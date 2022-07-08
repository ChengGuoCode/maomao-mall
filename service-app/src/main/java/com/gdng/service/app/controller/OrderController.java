package com.gdng.service.app.controller;

import com.gdng.inner.api.order.dto.OrderCloseReqDTO;
import com.gdng.inner.api.order.dto.OrderCreateReqDTO;
import com.gdng.inner.api.order.dto.OrderCreateResDTO;
import com.gdng.inner.api.order.dto.OrderRefundReqDTO;
import com.gdng.inner.api.order.invoke.OrderRemote;
import com.gdng.support.common.dto.res.ResDTO;
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

    @PostMapping("/close")
    public ResDTO<?> close(@RequestBody OrderCloseReqDTO reqDTO) {
        return orderRemote.close(reqDTO);
    }

    @PostMapping("/refund")
    public ResDTO<?> refund(@RequestBody OrderRefundReqDTO reqDTO) {
        return orderRemote.refund(reqDTO);
    }

}
