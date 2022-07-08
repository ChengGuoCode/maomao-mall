package com.gdng.core.order.service;

import com.gdng.inner.api.order.dto.OrderCreateReqDTO;
import com.gdng.inner.api.order.dto.OrderCreateResDTO;

public interface OrderService {

    OrderCreateResDTO create(OrderCreateReqDTO reqDTO);

}
