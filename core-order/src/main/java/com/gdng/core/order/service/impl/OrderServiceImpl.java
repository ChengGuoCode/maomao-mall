package com.gdng.core.order.service.impl;

import com.gdng.core.order.dao.service.OrderDaoService;
import com.gdng.core.order.dao.service.OrderDetailDaoService;
import com.gdng.core.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderDaoService orderDaoService;
    private final OrderDetailDaoService orderDetailDaoService;

    @Autowired
    public OrderServiceImpl(OrderDaoService orderDaoService, OrderDetailDaoService orderDetailDaoService) {
        this.orderDaoService = orderDaoService;
        this.orderDetailDaoService = orderDetailDaoService;
    }
}
