package com.gdng.service.app.controller;

import com.gdng.inner.api.order.dto.OrderCreateReqDTO;
import com.gdng.inner.api.order.dto.OrderPayReqDTO;
import com.gdng.inner.api.order.dto.OrderRefundReqDTO;
import com.gdng.inner.api.order.invoke.OrderRemote;
import com.gdng.support.common.dto.res.ResDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public ResDTO<?> create(@RequestBody List<OrderCreateReqDTO> reqDTOList) {
        return orderRemote.create(reqDTOList);
    }

    @PostMapping("/pay")
    public ResDTO<?> pay(@RequestBody OrderPayReqDTO reqDTO) {
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
