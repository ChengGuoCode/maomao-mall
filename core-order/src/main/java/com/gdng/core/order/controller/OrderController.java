package com.gdng.core.order.controller;

import com.gdng.core.order.service.OrderService;
import com.gdng.inner.api.order.dto.OrderCreateReqDTO;
import com.gdng.inner.api.order.dto.OrderPayReqDTO;
import com.gdng.inner.api.order.dto.OrderRefundReqDTO;
import com.gdng.support.common.dto.res.ResDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("core/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create")
    public ResDTO<?> create(@RequestBody List<OrderCreateReqDTO> reqDTOList) {
        orderService.create(reqDTOList);
        return ResDTO.buildSuccessResult();
    }

    @PostMapping("/pay")
    public ResDTO<?> pay(@RequestBody OrderPayReqDTO reqDTO) {
        return ResDTO.buildSuccessResult();
    }

    @PostMapping("/close")
    public ResDTO<?> close(@RequestBody OrderPayReqDTO reqDTO) {
        return ResDTO.buildSuccessResult();
    }

    @PostMapping("/refund")
    public ResDTO<?> refund(@RequestBody OrderRefundReqDTO reqDTO) {
        return ResDTO.buildSuccessResult();
    }

}
