package com.gdng.core.payment.controller;

import com.gdng.core.payment.service.PaymentService;
import com.gdng.inner.api.payment.dto.OrderPayReqDTO;
import com.gdng.inner.api.payment.dto.OrderPayResDTO;
import com.gdng.support.common.dto.res.ResDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/8 16:11
 * @Description:
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("core/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/pay")
    ResDTO<OrderPayResDTO> pay(@RequestBody OrderPayReqDTO reqDTO) {
        return ResDTO.buildSuccessResult(paymentService.pay(reqDTO));
    }

}
