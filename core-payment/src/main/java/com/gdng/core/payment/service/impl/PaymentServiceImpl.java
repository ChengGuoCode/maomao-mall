package com.gdng.core.payment.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.gdng.core.payment.constant.AccountStatusEnum;
import com.gdng.core.payment.constant.AccountTypeEnum;
import com.gdng.core.payment.constant.PayWayEnum;
import com.gdng.core.payment.dao.service.AccountDaoService;
import com.gdng.core.payment.dao.service.OrderPayDaoService;
import com.gdng.core.payment.dao.service.OrderPayDetailDaoService;
import com.gdng.core.payment.dao.service.OrderRefundDaoService;
import com.gdng.core.payment.service.PaymentService;
import com.gdng.entity.payment.po.AccountPO;
import com.gdng.inner.api.payment.dto.OrderPayReqDTO;
import com.gdng.inner.api.payment.dto.OrderPayResDTO;
import com.gdng.support.common.IdGenerator;
import com.gdng.support.common.dto.res.GlobalResponseEnum;
import com.gdng.support.common.exception.GdngException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

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

    private static final IdGenerator payNoGenerator = new IdGenerator("P");

    @Autowired
    public PaymentServiceImpl(AccountDaoService accountDaoService, OrderPayDaoService payDaoService, OrderPayDetailDaoService payDetailDaoService, OrderRefundDaoService refundDaoService) {
        this.accountDaoService = accountDaoService;
        this.payDaoService = payDaoService;
        this.payDetailDaoService = payDetailDaoService;
        this.refundDaoService = refundDaoService;
    }

    @Override
    @Transactional
    public OrderPayResDTO pay(OrderPayReqDTO reqDTO) {
        checkPayReq(reqDTO);
        PayWayEnum payWay = PayWayEnum.getPayWayByCode(reqDTO.getPayWay());
        OrderPayResDTO resDTO = new OrderPayResDTO();
        switch (payWay) {
            case BALANCE:
                String payerUid = reqDTO.getPayerUid();
                String pass = reqDTO.getPass();
                Long payment = reqDTO.getPayment();
                AccountPO accountPO = accountDaoService.getOne(new QueryWrapper<AccountPO>().eq("type", AccountTypeEnum.INDIVIDUAL.getType()).eq("corelation_id", payerUid));
                checkPayerAccount(accountPO, pass, payment);

                break;
        }
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
