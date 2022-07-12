package com.gdng.core.order.service.impl;

import com.gdng.core.order.constant.OrderSourceEnum;
import com.gdng.core.order.dao.service.OrderDaoService;
import com.gdng.core.order.dao.service.OrderDetailDaoService;
import com.gdng.core.order.service.OrderService;
import com.gdng.entity.order.po.OrderDetailPO;
import com.gdng.entity.order.po.OrderPO;
import com.gdng.inner.api.order.dto.OrderCreateReqDTO;
import com.gdng.inner.api.order.dto.OrderCreateResDTO;
import com.gdng.inner.api.order.dto.OrderItemDTO;
import com.gdng.support.common.IdGenerator;
import com.gdng.support.common.dto.res.GlobalResponseEnum;
import com.gdng.support.common.exception.GdngException;
import com.gdng.support.common.util.GdngBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
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
    public OrderCreateResDTO create(OrderCreateReqDTO reqDTO) {
        List<OrderItemDTO> orderItemList = reqDTO.getOrderItemList();
        Integer orderSource = reqDTO.getOrderSource();
        if (CollectionUtils.isEmpty(orderItemList)) {
            throw new GdngException(GlobalResponseEnum.BIZ_PARAM_ERR, "购物车商品信息不能为空");
        }
        if (orderSource == null || OrderSourceEnum.getSourceByCode(orderSource) == null) {
            throw new GdngException(GlobalResponseEnum.BIZ_PARAM_ERR, "无效的订单来源");
        }

        String orderNo = orderNoGenerator.nextId();
        AtomicLong orderPrice = new AtomicLong(0);
        AtomicLong payment = new AtomicLong(0);
        List<OrderDetailPO> orderDetailPOList = orderItemList.stream().map(orderDTO -> {
            OrderDetailPO orderDetailPO = GdngBeanUtil.copyToNewBean(orderDTO, OrderDetailPO.class);
            orderDetailPO.setOrderNo(orderNo);
            long originPrice = orderDTO.getPrice() * orderDTO.getGoodsNum();
            long actualPay = originPrice;
            orderDetailPO.setPayment(actualPay);
            orderDetailPO.setBeneficiary(orderDTO.getBeneficiary());
            orderPrice.addAndGet(originPrice);
            payment.addAndGet(actualPay);
            return orderDetailPO;
        }).collect(Collectors.toList());
        orderDetailDaoService.saveBatch(orderDetailPOList);

        OrderPO orderPO = new OrderPO();
        orderPO.setOrderNo(orderNo);
        orderPO.setOrderPrice(orderPrice.get());
        orderPO.setPayment(payment.get());
        orderPO.setOrderSource(orderSource);
        orderDaoService.save(orderPO);

        OrderCreateResDTO orderCreateResDTO = new OrderCreateResDTO();
        orderCreateResDTO.setOrderNo(orderPO.getOrderNo());
        return orderCreateResDTO;
    }
}
