package com.gdng.core.order.service;

import com.gdng.inner.api.order.dto.OrderCreateReqDTO;
import com.gdng.inner.api.order.dto.OrderCreateResDTO;
import com.gdng.inner.api.payment.dto.mq.PayCallbackDTO;

public interface OrderService {

    OrderCreateResDTO create(OrderCreateReqDTO reqDTO);

    void payCallback(PayCallbackDTO payCallbackDTO);

}
