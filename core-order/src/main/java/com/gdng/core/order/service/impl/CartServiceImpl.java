package com.gdng.core.order.service.impl;

import com.gdng.core.order.dao.service.OrderCartDaoService;
import com.gdng.core.order.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    private final OrderCartDaoService cartDaoService;

    @Autowired
    public CartServiceImpl(OrderCartDaoService cartDaoService) {
        this.cartDaoService = cartDaoService;
    }
}
