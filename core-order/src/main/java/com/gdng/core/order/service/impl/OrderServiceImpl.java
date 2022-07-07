package com.gdng.core.order.service.impl;

import com.gdng.core.order.dao.service.OrderDaoService;
import com.gdng.core.order.dao.service.OrderDetailDaoService;
import com.gdng.core.order.service.OrderService;
import com.gdng.entity.order.po.OrderDetailPO;
import com.gdng.entity.order.po.OrderPO;
import com.gdng.inner.api.order.dto.OrderCreateReqDTO;
import com.gdng.inner.api.order.dto.OrderCreateResDTO;
import com.gdng.support.common.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderDaoService orderDaoService;
    private final OrderDetailDaoService orderDetailDaoService;

    private static final IdGenerator orderNoGenerator = new IdGenerator("O");

    @Autowired
    public OrderServiceImpl(OrderDaoService orderDaoService, OrderDetailDaoService orderDetailDaoService) {
        this.orderDaoService = orderDaoService;
        this.orderDetailDaoService = orderDetailDaoService;
    }

    @Override
    @Transactional
    public OrderCreateResDTO create(List<OrderCreateReqDTO> reqDTOList) {
        List<OrderDetailPO> orderDetailPOList = reqDTOList.stream().map(orderDTO -> {
            OrderDetailPO orderDetailPO = new OrderDetailPO();
            return orderDetailPO;
        }).collect(Collectors.toList());
        orderDetailDaoService.saveBatch(orderDetailPOList);

        OrderPO orderPO = new OrderPO();
        orderPO.setOrderNo(orderNoGenerator.nextId());
        orderDaoService.save(orderPO);

        OrderCreateResDTO orderCreateResDTO = new OrderCreateResDTO();
        orderCreateResDTO.setOrderNo(orderPO.getOrderNo());
        return orderCreateResDTO;
    }
}
