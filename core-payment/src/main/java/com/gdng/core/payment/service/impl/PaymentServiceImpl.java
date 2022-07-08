package com.gdng.core.payment.service.impl;

import com.gdng.core.payment.dao.service.AccountDaoService;
import com.gdng.core.payment.dao.service.OrderPayDaoService;
import com.gdng.core.payment.dao.service.OrderPayDetailDaoService;
import com.gdng.core.payment.dao.service.OrderRefundDaoService;
import com.gdng.core.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    public PaymentServiceImpl(AccountDaoService accountDaoService, OrderPayDaoService payDaoService, OrderPayDetailDaoService payDetailDaoService, OrderRefundDaoService refundDaoService) {
        this.accountDaoService = accountDaoService;
        this.payDaoService = payDaoService;
        this.payDetailDaoService = payDetailDaoService;
        this.refundDaoService = refundDaoService;
    }
}
