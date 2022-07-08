package com.gdng.core.order.controller;

import com.gdng.core.order.service.OrderService;
import com.gdng.inner.api.order.dto.OrderCloseReqDTO;
import com.gdng.inner.api.order.dto.OrderCreateReqDTO;
import com.gdng.inner.api.order.dto.OrderCreateResDTO;
import com.gdng.inner.api.order.dto.OrderRefundReqDTO;
import com.gdng.support.common.dto.res.ResDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("core/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create")
    public ResDTO<OrderCreateResDTO> create(@RequestBody @Valid @NotNull(message = "购物车订单信息不能为空") OrderCreateReqDTO reqDTO) {
        return ResDTO.buildSuccessResult(orderService.create(reqDTO));
    }

    @PostMapping("/close")
    public ResDTO<?> close(@RequestBody OrderCloseReqDTO reqDTO) {
        return ResDTO.buildSuccessResult();
    }

    @PostMapping("/refund")
    public ResDTO<?> refund(@RequestBody OrderRefundReqDTO reqDTO) {
        return ResDTO.buildSuccessResult();
    }

}
