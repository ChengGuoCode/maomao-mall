package com.gdng.core.order.service;

import com.gdng.inner.api.order.dto.OrderCreateReqDTO;

import java.util.List;

public interface OrderService {

    void create(List<OrderCreateReqDTO> reqDTOList);

}
