package com.gdng.service.app.controller;

import com.gdng.inner.api.order.dto.*;
import com.gdng.inner.api.order.invoke.OrderRemote;
import com.gdng.support.common.dto.UserDTO;
import com.gdng.support.common.dto.res.ResDTO;
import com.gdng.support.common.spring.SpringContextHolder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/5 14:49
 * @Description:
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/service/app/order")
public class OrderController {

    private final OrderRemote orderRemote;

    @Autowired
    public OrderController(OrderRemote orderRemote) {
        this.orderRemote = orderRemote;
    }

    @PostMapping("/create")
    public ResDTO<OrderCreateResDTO> create(@RequestBody OrderCreateReqDTO reqDTO) {
        return orderRemote.create(reqDTO);
    }

    @PostMapping("/pay")
    public ResDTO<OrderPayResDTO> pay(@RequestBody OrderPayReqDTO reqDTO) {
        if (StringUtils.isBlank(reqDTO.getPayerUid())) {
            UserDTO user = SpringContextHolder.getUser();
            reqDTO.setPayerUid(user.getId());
        }
        return orderRemote.pay(reqDTO);
    }

    @PostMapping("/close")
    public ResDTO<?> close(@RequestBody OrderPayReqDTO reqDTO) {
        return orderRemote.close(reqDTO);
    }

    @PostMapping("/refund")
    public ResDTO<?> refund(@RequestBody OrderRefundReqDTO reqDTO) {
        return orderRemote.refund(reqDTO);
    }

}
