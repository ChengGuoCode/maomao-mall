package com.gdng.core.payment.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gdng.core.payment.constant.AccountStatusEnum;
import com.gdng.core.payment.constant.PayWayEnum;
import com.gdng.core.payment.dao.service.AccountDaoService;
import com.gdng.core.payment.dao.service.OrderPayDaoService;
import com.gdng.core.payment.dao.service.OrderPayDetailDaoService;
import com.gdng.core.payment.dao.service.OrderRefundDaoService;
import com.gdng.core.payment.mq.pay.PayCallbackProducer;
import com.gdng.core.payment.service.PaymentService;
import com.gdng.entity.payment.po.AccountPO;
import com.gdng.entity.payment.po.OrderPayDetailPO;
import com.gdng.entity.payment.po.OrderPayPO;
import com.gdng.inner.api.merchant.dto.StoreDTO;
import com.gdng.inner.api.merchant.invoke.StoreRemote;
import com.gdng.inner.api.payment.dto.AccountTypeEnum;
import com.gdng.inner.api.payment.dto.OrderPayItemDTO;
import com.gdng.inner.api.payment.dto.OrderPayReqDTO;
import com.gdng.inner.api.payment.dto.OrderPayResDTO;
import com.gdng.inner.api.payment.dto.mq.PayCallbackDTO;
import com.gdng.support.common.IdGenerator;
import com.gdng.support.common.dto.res.GlobalResponseEnum;
import com.gdng.support.common.dto.res.ResDTO;
import com.gdng.support.common.exception.GdngException;
import com.gdng.support.common.lock.RedisLock;
import com.gdng.support.common.util.JacksonUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

import static com.gdng.core.payment.constant.RedisLockType.ACCOUNT_LOCK;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/8 16:12
 * @Description:
 * @Version: 1.0.0
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    private final AccountDaoService accountDaoService;
    private final OrderPayDaoService payDaoService;
    private final OrderPayDetailDaoService payDetailDaoService;
    private final OrderRefundDaoService refundDaoService;
    private final PayCallbackProducer payCallbackProducer;
    private final StoreRemote storeRemote;
    private final PlatformTransactionManager platformTransactionManager;
    private final TransactionDefinition transactionDefinition;

    private static final Logger log = LoggerFactory.getLogger(PaymentServiceImpl.class);
    private static final IdGenerator payNoGenerator = new IdGenerator("P");

    @Autowired
    public PaymentServiceImpl(AccountDaoService accountDaoService, OrderPayDaoService payDaoService, OrderPayDetailDaoService payDetailDaoService, OrderRefundDaoService refundDaoService, PayCallbackProducer payCallbackProducer, StoreRemote storeRemote, PlatformTransactionManager platformTransactionManager, TransactionDefinition transactionDefinition) {
        this.accountDaoService = accountDaoService;
        this.payDaoService = payDaoService;
        this.payDetailDaoService = payDetailDaoService;
        this.refundDaoService = refundDaoService;
        this.payCallbackProducer = payCallbackProducer;
        this.storeRemote = storeRemote;
        this.platformTransactionManager = platformTransactionManager;
        this.transactionDefinition = transactionDefinition;
    }

    @Override
    public OrderPayResDTO pay(OrderPayReqDTO reqDTO) {
        checkPayParam(reqDTO);
        PayWayEnum payWay = PayWayEnum.getPayWayByCode(reqDTO.getPayWay());
        int payWayCode = Objects.requireNonNull(payWay).getCode();
        String payerUid = reqDTO.getPayerUid();
        String pass = reqDTO.getPass();
        Long payment = reqDTO.getPayment();
        String orderNo = reqDTO.getOrderNo();
        List<OrderPayItemDTO> orderPayItemList = reqDTO.getOrderPayItemList();

        OrderPayResDTO resDTO = new OrderPayResDTO();
        Date payTime = null;
        String payNo = payNoGenerator.nextId();

        if (payWay == PayWayEnum.BALANCE) {
            List<Long> storeIdList = orderPayItemList.stream().map(OrderPayItemDTO::getStoreId).distinct().collect(Collectors.toList());
            ResDTO<List<StoreDTO>> storeByIds = storeRemote.getStoreByIds(storeIdList);
            if (!storeByIds.isSuccess()) {
                log.error("远程接口StoreRemote.getStoreByIds调用异常，入参：{}", storeIdList);
                throw new GdngException(GlobalResponseEnum.REMOTE_ERR, "StoreRemote.getStoreByIds");
            }
            List<StoreDTO> storeDTOList = storeByIds.getData();
            if (CollectionUtils.isEmpty(storeDTOList)) {
                throw new GdngException(GlobalResponseEnum.BIZ_PARAM_ERR, "商品店铺不存在");
            }
            Map<Long, Long> storeBeneficiaryMap = new HashMap<>(storeDTOList.stream().collect(Collectors.toMap(StoreDTO::getId, StoreDTO::getBeneficiary)));

            AccountPO accountPO = accountDaoService.getOne(new QueryWrapper<AccountPO>().eq("type", AccountTypeEnum.INDIVIDUAL.getType()).eq("corelation_id", payerUid));
            checkPayerAccount(accountPO, pass, payment);

            Set<Long> accountIdList = new TreeSet<>();
            accountIdList.add(accountPO.getId());
            accountIdList.addAll(storeBeneficiaryMap.values());
            List<RedisLock> payLocks = getPayLocks(accountIdList);

            TransactionStatus transaction = platformTransactionManager.getTransaction(transactionDefinition);
            try {
                accountPO = accountDaoService.getById(accountPO.getId());
                accountPO.setBalance(accountPO.getBalance() - payment);

                List<AccountPO> accountPOList = accountDaoService.listByIds(storeBeneficiaryMap.values());
                if (CollectionUtils.isEmpty(accountPOList)) {
                    throw new GdngException(GlobalResponseEnum.BIZ_PARAM_ERR, "店铺收款账户不存在");
                }
                Map<Long, AccountPO> accountPOMap = accountPOList.stream().collect(Collectors.toMap(AccountPO::getId, e -> e));
                orderPayItemList.forEach(payItem -> {
                    Long storeId = payItem.getStoreId();
                    Long beneficiary = storeBeneficiaryMap.get(storeId);
                    if (beneficiary == null) {
                        throw new GdngException(GlobalResponseEnum.BIZ_PARAM_ERR, "店铺[" + storeId + "]对应收款账户不存在");
                    }
                    AccountPO beneficiaryAcc = accountPOMap.get(beneficiary);
                    beneficiaryAcc.setBalance(beneficiaryAcc.getBalance() + (payItem.getPrice() * payItem.getGoodsNum()));
                });
                accountPOList.add(accountPO);
                accountDaoService.updateBatchById(accountPOList);

                payTime = savePayData(payWayCode, payment, orderNo, orderPayItemList, accountPO, payNo, storeBeneficiaryMap);
                platformTransactionManager.commit(transaction);
            } catch (Exception e) {
                log.error("余额支付异常", e);
                platformTransactionManager.rollback(transaction);
                throw new GdngException(GlobalResponseEnum.BIZ_PARAM_ERR, "余额支付异常，请稍后重试");
            } finally {
                if (!CollectionUtils.isEmpty(payLocks)) {
                    for (RedisLock payLock : payLocks) {
                        try {
                            payLock.unlock();
                        } catch (Exception e) {
                            log.error("支付账户锁释放异常", e);
                        }
                    }
                }
            }
        }

        PayCallbackDTO payCallbackDTO = new PayCallbackDTO();
        payCallbackDTO.setOrderNo(orderNo);
        payCallbackDTO.setPayWay(payWayCode);
        payCallbackDTO.setPayTime(payTime);
        payCallbackDTO.setPayOrderNo(payNo);
        payCallbackDTO.setPayerUid(payerUid);
        try {
            payCallbackProducer.sendMsg(payCallbackDTO);
        } catch (Exception e) {
            log.error("支付回调消息推送异常：{}", JacksonUtil.anyToJson(payCallbackDTO));
        }

        resDTO.setPayNo(payNo);
        return resDTO;
    }

    private List<RedisLock> getPayLocks(Set<Long> accountIdList) {
        List<RedisLock> lockList = new ArrayList<>();
        for (Long lockKey : accountIdList) {
            RedisLock redisLock = new RedisLock(ACCOUNT_LOCK);
            boolean success = redisLock.lock(String.valueOf(lockKey));
            if (!success) {
                for (RedisLock alreadyLock : lockList) {
                    try {
                        alreadyLock.unlock();
                    } catch (Exception e) {
                        log.error("支付账户锁释放异常:{}", lockKey, e);
                    }
                }
                throw new GdngException(GlobalResponseEnum.BIZ_PARAM_ERR, "获取支付账户锁异常，请稍后重试");
            }
            lockList.add(redisLock);
        }
        return lockList;
    }

    private Date savePayData(Integer payWayCode, Long payment, String orderNo, List<OrderPayItemDTO> orderPayItemList,
                               AccountPO accountPO, String payNo, Map<Long, Long> storeBeneficiaryMap) {
        OrderPayPO orderPayPO = new OrderPayPO();
        orderPayPO.setPayNo(payNo);
        orderPayPO.setOrderNo(orderNo);
        orderPayPO.setPayment(payment);
        orderPayPO.setPayWay(payWayCode);
        orderPayPO.setPayAcc(accountPO == null ? null : accountPO.getId());
        payDaoService.save(orderPayPO);

        List<OrderPayDetailPO> orderPayDetailPOList = orderPayItemList.stream().map(payItem -> {
            OrderPayDetailPO orderPayDetailPO = new OrderPayDetailPO();
            orderPayDetailPO.setPayNo(payNo);
            orderPayDetailPO.setOrderNo(orderNo);
            orderPayDetailPO.setPayment(payItem.getPrice());
            orderPayDetailPO.setPayWay(payWayCode);
            orderPayDetailPO.setPayAcc(accountPO == null ? null : accountPO.getId());
            orderPayDetailPO.setBusinessId(payItem.getBusinessId());
            Long storeId = payItem.getStoreId();
            orderPayDetailPO.setBeneficiary(storeBeneficiaryMap.get(storeId));
            orderPayDetailPO.setStoreId(storeId);
            orderPayDetailPO.setProductId(payItem.getProductId());
            orderPayDetailPO.setSkuCode(payItem.getSkuCode());
            orderPayDetailPO.setPrice(payItem.getPrice());
            orderPayDetailPO.setNum(payItem.getGoodsNum());
            return orderPayDetailPO;
        }).collect(Collectors.toList());
        payDetailDaoService.saveBatch(orderPayDetailPOList);
        return orderPayPO.getCreateTime();
    }

    private void checkPayParam(OrderPayReqDTO reqDTO) {
        if (StringUtils.isBlank(reqDTO.getOrderNo())) {
            throw new GdngException(GlobalResponseEnum.BIZ_PARAM_ERR, "订单编号不能为空");
        }
        if (reqDTO.getPayment() == null || reqDTO.getPayment() <= 0) {
            throw new GdngException(GlobalResponseEnum.BIZ_PARAM_ERR, "无效的支付金额");
        }
        if (reqDTO.getPayWay() == null || PayWayEnum.getPayWayByCode(reqDTO.getPayWay()) == null) {
            throw new GdngException(GlobalResponseEnum.BIZ_PARAM_ERR, "无效的支付类型");
        }
        if (StringUtils.isBlank(reqDTO.getPayerUid())) {
            throw new GdngException(GlobalResponseEnum.BIZ_PARAM_ERR, "支付用户不能为空");
        }
        if (StringUtils.isBlank(reqDTO.getPass())) {
            throw new GdngException(GlobalResponseEnum.BIZ_PARAM_ERR, "支付密码不能为空");
        }
        if (CollectionUtils.isEmpty(reqDTO.getOrderPayItemList())) {
            throw new GdngException(GlobalResponseEnum.BIZ_PARAM_ERR, "购买商品明细不能为空");
        }
    }

    private void checkPayerAccount(AccountPO accountPO, String pass, Long payment) {
        if (accountPO == null) {
            throw new GdngException(GlobalResponseEnum.BIZ_PARAM_ERR, "付款账户不存在");
        }
        if (accountPO.getAccStatus() != AccountStatusEnum.NORMAL.getStatus() && accountPO.getAccStatus() != AccountStatusEnum.PAYABLE.getStatus()) {
            throw new GdngException(GlobalResponseEnum.BIZ_PARAM_ERR, "账户暂不可用，请联系管理员");
        }
        if (!pass.equals(accountPO.getPayPass())) {
            throw new GdngException(GlobalResponseEnum.BIZ_PARAM_ERR, "支付密码错误");
        }
        if (accountPO.getBalance() < payment) {
            throw new GdngException(GlobalResponseEnum.BIZ_PARAM_ERR, "账户余额不足");
        }
    }
}
