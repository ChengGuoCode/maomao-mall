package com.gdng.service.app.controller;

import com.gdng.inner.api.payment.dto.OrderPayReqDTO;
import com.gdng.inner.api.payment.dto.OrderPayResDTO;
import com.gdng.inner.api.payment.invoke.PaymentRemote;
import com.gdng.support.common.dto.UserDTO;
import com.gdng.support.common.dto.res.ResDTO;
import com.gdng.support.common.spring.SpringContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/8 16:29
 * @Description:
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/service/app/payment")
public class PaymentController {

    private final PaymentRemote paymentRemote;

    @Autowired
    public PaymentController(PaymentRemote paymentRemote) {
        this.paymentRemote = paymentRemote;
    }

    @PostMapping("/pay")
    public ResDTO<OrderPayResDTO> pay(@RequestBody OrderPayReqDTO reqDTO) {
        UserDTO user = SpringContextHolder.getUser();
        reqDTO.setPayerUid(user.getId());
        return paymentRemote.pay(reqDTO);
    }
}
