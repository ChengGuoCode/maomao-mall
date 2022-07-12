package com.gdng.core.payment.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gdng.core.payment.constant.AccountStatusEnum;
import com.gdng.core.payment.constant.AccountTypeEnum;
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
import com.gdng.inner.api.payment.dto.OrderPayItemDTO;
import com.gdng.inner.api.payment.dto.OrderPayReqDTO;
import com.gdng.inner.api.payment.dto.OrderPayResDTO;
import com.gdng.inner.api.payment.dto.mq.PayCallbackDTO;
import com.gdng.support.common.IdGenerator;
import com.gdng.support.common.dto.res.GlobalResponseEnum;
import com.gdng.support.common.exception.GdngException;
import com.gdng.support.common.lock.RedisLock;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

import static com.gdng.core.payment.constant.RedisLockType.INDIVIDUAL_ACCOUNT_LOCK;

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

    private static final IdGenerator payNoGenerator = new IdGenerator("P");

    @Autowired
    public PaymentServiceImpl(AccountDaoService accountDaoService, OrderPayDaoService payDaoService, OrderPayDetailDaoService payDetailDaoService, OrderRefundDaoService refundDaoService, PayCallbackProducer payCallbackProducer) {
        this.accountDaoService = accountDaoService;
        this.payDaoService = payDaoService;
        this.payDetailDaoService = payDetailDaoService;
        this.refundDaoService = refundDaoService;
        this.payCallbackProducer = payCallbackProducer;
    }

    @Override
    @Transactional
    public OrderPayResDTO pay(OrderPayReqDTO reqDTO) {
        checkPayReq(reqDTO);
        PayWayEnum payWay = PayWayEnum.getPayWayByCode(reqDTO.getPayWay());
        String payerUid = reqDTO.getPayerUid();
        String pass = reqDTO.getPass();
        Long payment = reqDTO.getPayment();
        String orderNo = reqDTO.getOrderNo();
        List<OrderPayItemDTO> orderPayItemList = reqDTO.getOrderPayItemList();

        OrderPayResDTO resDTO = new OrderPayResDTO();
        AccountPO accountPO = null;
        switch (payWay) {
            case BALANCE:
                RedisLock redisLock = new RedisLock(INDIVIDUAL_ACCOUNT_LOCK);
                boolean lock = redisLock.lock(payerUid);
                if (!lock) {
                    throw new GdngException(GlobalResponseEnum.SYSTEM_BUSY);
                }
                try {
                    accountPO = accountDaoService.getOne(new QueryWrapper<AccountPO>().eq("type", AccountTypeEnum.INDIVIDUAL.getType()).eq("corelation_id", payerUid));
                    checkPayerAccount(accountPO, pass, payment);
                    accountPO.setBalance(accountPO.getBalance() - payment);
                    accountDaoService.updateById(accountPO);
                } finally {
                    redisLock.unlock();
                }
                break;
        }
        String payNo = payNoGenerator.nextId();
        AccountPO finalAccountPO = accountPO;

        OrderPayPO orderPayPO = new OrderPayPO();
        orderPayPO.setPayNo(payNo);
        orderPayPO.setOrderNo(orderNo);
        orderPayPO.setPayment(payment);
        orderPayPO.setPayWay(payWay.getCode());
        orderPayPO.setPayAcc(finalAccountPO == null ? null : finalAccountPO.getId());
        payDaoService.save(orderPayPO);

        List<OrderPayDetailPO> orderPayDetailPOList = orderPayItemList.stream().map(payItem -> {
            OrderPayDetailPO orderPayDetailPO = new OrderPayDetailPO();
            orderPayDetailPO.setPayNo(payNo);
            orderPayDetailPO.setOrderNo(orderNo);
            orderPayDetailPO.setPayment(payItem.getPrice());
            orderPayDetailPO.setPayWay(payWay.getCode());
            orderPayDetailPO.setPayAcc(finalAccountPO == null ? null : finalAccountPO.getId());
            orderPayDetailPO.setBeneficiary(payItem.getBeneficiary());
            orderPayDetailPO.setBusinessId(payItem.getBusinessId());
            orderPayDetailPO.setStoreId(payItem.getStoreId());
            orderPayDetailPO.setProductId(payItem.getProductId());
            orderPayDetailPO.setSkuCode(payItem.getSkuCode());
            orderPayDetailPO.setPrice(payItem.getPrice());
            orderPayDetailPO.setNum(payItem.getGoodsNum());
            return orderPayDetailPO;
        }).collect(Collectors.toList());
        payDetailDaoService.saveBatch(orderPayDetailPOList);

        PayCallbackDTO payCallbackDTO = new PayCallbackDTO();
        payCallbackProducer.sendMsg(payCallbackDTO);

        resDTO.setPayNo(payNo);
        return resDTO;
    }

    private void checkPayReq(OrderPayReqDTO reqDTO) {
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
