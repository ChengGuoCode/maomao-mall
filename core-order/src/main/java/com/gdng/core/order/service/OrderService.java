package com.gdng.core.order.service;

import com.gdng.inner.api.order.dto.OrderCreateReqDTO;
import com.gdng.inner.api.order.dto.OrderCreateResDTO;
import com.gdng.inner.api.order.dto.OrderPayReqDTO;
import com.gdng.inner.api.order.dto.OrderPayResDTO;

public interface OrderService {

    OrderCreateResDTO create(OrderCreateReqDTO reqDTO);

    OrderPayResDTO pay(OrderPayReqDTO reqDTO);

}
