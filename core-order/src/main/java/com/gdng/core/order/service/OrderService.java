package com.gdng.core.order.service;

import com.gdng.inner.api.order.dto.OrderCreateReqDTO;
import com.gdng.inner.api.order.dto.OrderCreateResDTO;

import java.util.List;

public interface OrderService {

    OrderCreateResDTO create(List<OrderCreateReqDTO> reqDTOList);

}
