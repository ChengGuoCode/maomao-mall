package com.gdng.core.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gdng.core.order.constant.OrderSourceEnum;
import com.gdng.core.order.constant.OrderStatusEnum;
import com.gdng.core.order.dao.service.OrderActivityDaoService;
import com.gdng.core.order.dao.service.OrderCartDaoService;
import com.gdng.core.order.dao.service.OrderDaoService;
import com.gdng.core.order.dao.service.OrderDetailDaoService;
import com.gdng.core.order.service.OrderService;
import com.gdng.entity.order.po.OrderActivityPO;
import com.gdng.entity.order.po.OrderDetailPO;
import com.gdng.entity.order.po.OrderPO;
import com.gdng.inner.api.goods.dto.StoreProductSkuStockDTO;
import com.gdng.inner.api.goods.invoke.StoreProductRemote;
import com.gdng.inner.api.order.dto.OrderCreateReqDTO;
import com.gdng.inner.api.order.dto.OrderCreateResDTO;
import com.gdng.inner.api.order.dto.OrderItemDTO;
import com.gdng.inner.api.payment.dto.mq.PayCallbackDTO;
import com.gdng.inner.api.task.dto.RewardFallbackReqDTO;
import com.gdng.inner.api.task.dto.mq.GoodsSendDTO;
import com.gdng.inner.api.task.dto.mq.GoodsSendItemDTO;
import com.gdng.inner.api.task.invoke.TaskRemote;
import com.gdng.support.common.IdGenerator;
import com.gdng.support.common.dto.res.GlobalResponseEnum;
import com.gdng.support.common.dto.res.ResDTO;
import com.gdng.support.common.exception.GdngException;
import com.gdng.support.common.util.GdngBeanUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final OrderDaoService orderDaoService;
    private final OrderDetailDaoService orderDetailDaoService;
    private final OrderActivityDaoService orderActivityDaoService;
    private final StoreProductRemote storeProductRemote;
    private final OrderCartDaoService cartDaoService;
    private final TaskRemote taskRemote;

    private static final IdGenerator orderNoGenerator = new IdGenerator("O");

    @Autowired
    public OrderServiceImpl(OrderDaoService orderDaoService, OrderDetailDaoService orderDetailDaoService, OrderActivityDaoService orderActivityDaoService, StoreProductRemote storeProductRemote, OrderCartDaoService cartDaoService, TaskRemote taskRemote) {
        this.orderDaoService = orderDaoService;
        this.orderDetailDaoService = orderDetailDaoService;
        this.orderActivityDaoService = orderActivityDaoService;
        this.storeProductRemote = storeProductRemote;
        this.cartDaoService = cartDaoService;
        this.taskRemote = taskRemote;
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
        List<StoreProductSkuStockDTO> reqDTOs = new ArrayList<>();
        List<Long> cartIdList = new ArrayList<>();
        List<OrderDetailPO> orderDetailPOList = orderItemList.stream().map(orderDTO -> {
            cartIdList.add(orderDTO.getCartId());
            OrderDetailPO orderDetailPO = GdngBeanUtil.copyToNewBean(orderDTO, OrderDetailPO.class);
            orderDetailPO.setOrderNo(orderNo);
            long originPrice = orderDTO.getPrice() * orderDTO.getGoodsNum();
            long actualPay = originPrice;
            orderDetailPO.setPayment(actualPay);
            orderPrice.addAndGet(originPrice);
            payment.addAndGet(actualPay);

            StoreProductSkuStockDTO skuStockDTO = new StoreProductSkuStockDTO();
            skuStockDTO.setBusinessId(orderDTO.getBusinessId());
            skuStockDTO.setStoreId(orderDTO.getStoreId());
            skuStockDTO.setProductId(orderDTO.getProductId());
            skuStockDTO.setSkuCode(orderDTO.getSkuCode());
            skuStockDTO.setGoodsNum(orderDTO.getGoodsNum());
            skuStockDTO.setPayment(actualPay);
            reqDTOs.add(skuStockDTO);

            return orderDetailPO;
        }).collect(Collectors.toList());
        orderDetailDaoService.saveBatch(orderDetailPOList);

        long paymentL = payment.get();
        if (paymentL == 0L) {
            storeProductRemote.reduceStock(reqDTOs);
        } else {
            storeProductRemote.lockStock(reqDTOs);
        }

        OrderPO orderPO = new OrderPO();
        orderPO.setOrderNo(orderNo);
        orderPO.setOrderPrice(orderPrice.get());
        orderPO.setPayment(paymentL);
        orderPO.setOrderSource(orderSource);
        if (paymentL == 0L) {
            orderPO.setStatus(1);
        }
        orderDaoService.save(orderPO);

        if (OrderSourceEnum.getSourceByCode(orderSource) == OrderSourceEnum.TASK) {
            OrderActivityPO orderActivity = new OrderActivityPO();
            orderActivity.setOrderNo(orderNo);
            orderActivity.setType(0);
            orderActivity.setTaskId(reqDTO.getTaskId());
            orderActivity.setStrategyId(reqDTO.getStrategyId());
            orderActivityDaoService.save(orderActivity);
        }

        OrderCreateResDTO orderCreateResDTO = new OrderCreateResDTO();
        orderCreateResDTO.setOrderNo(orderPO.getOrderNo());

        if (!CollectionUtils.isEmpty(cartIdList)) {
            cartDaoService.removeByIds(cartIdList);
        }

        return orderCreateResDTO;
    }

    @Override
    public void payCallback(PayCallbackDTO payCallbackDTO) {
        checkPayCallbackParam(payCallbackDTO);

        String orderNo = payCallbackDTO.getOrderNo();
        Integer payWay = payCallbackDTO.getPayWay();
        Date payTime = payCallbackDTO.getPayTime();
        String payOrderNo = payCallbackDTO.getPayOrderNo();
        String payerUid = payCallbackDTO.getPayerUid();

        OrderPO orderPO = orderDaoService.getOne(new QueryWrapper<OrderPO>().eq("order_no", orderNo));
        if (orderPO == null) {
            throw new GdngException(GlobalResponseEnum.BIZ_PARAM_ERR, "支付回调无效订单编号：" + orderNo);
        }
        List<OrderDetailPO> orderDetailList = orderDetailDaoService.list(new QueryWrapper<OrderDetailPO>()
                .eq("order_no", orderNo));
        if (!CollectionUtils.isEmpty(orderDetailList)) {
            List<StoreProductSkuStockDTO> reqDTOs = orderDetailList.stream().map(orderDetail -> {
                StoreProductSkuStockDTO skuStockDTO = new StoreProductSkuStockDTO();
                skuStockDTO.setBusinessId(orderDetail.getBusinessId());
                skuStockDTO.setStoreId(orderDetail.getStoreId());
                skuStockDTO.setProductId(orderDetail.getProductId());
                skuStockDTO.setSkuCode(orderDetail.getSkuCode());
                skuStockDTO.setPayment(orderDetail.getPayment());
                skuStockDTO.setGoodsNum(orderDetail.getGoodsNum());
                return skuStockDTO;
            }).collect(Collectors.toList());
            ResDTO<?> resDTO = storeProductRemote.reduceStock(reqDTOs);
            if (!resDTO.isSuccess()) {
                log.error("商品库存扣减失败:{}", resDTO.getMsg());
                return;
            }
        }

        orderPO.setPayWay(payWay);
        orderPO.setPayTime(payTime);
        orderPO.setPayOrderNo(payOrderNo);
        orderPO.setPayerUid(payerUid);
        orderPO.setStatus(OrderStatusEnum.PAID.getStatus());
        orderDaoService.updateById(orderPO);
    }

    @Override
    public void sendGoods(GoodsSendDTO goodsSendDTO) {
        OrderCreateReqDTO reqDTO = new OrderCreateReqDTO();
        reqDTO.setOrderSource(OrderSourceEnum.TASK.getSourceCode());
        Long taskId = goodsSendDTO.getTaskId();
        reqDTO.setTaskId(taskId);
        Long strategyId = goodsSendDTO.getStrategyId();
        reqDTO.setStrategyId(strategyId);
        List<GoodsSendItemDTO> goodsItemList = goodsSendDTO.getGoodsItemList();
        List<OrderItemDTO> orderItemList = goodsItemList.stream().map(goodsItem -> {
            OrderItemDTO orderItemDTO = new OrderItemDTO();
            orderItemDTO.setBusinessId(goodsItem.getBusinessId());
            orderItemDTO.setStoreId(goodsItem.getStoreId());
            orderItemDTO.setProductId(goodsItem.getProductId());
            orderItemDTO.setProductName("");
            orderItemDTO.setSkuCode(goodsItem.getSkuCode());
            orderItemDTO.setGoodsNum(goodsItem.getSendNum());
            return orderItemDTO;
        }).collect(Collectors.toList());
        reqDTO.setOrderItemList(orderItemList);
        try {
            create(reqDTO);
        } catch (Exception e) {
            RewardFallbackReqDTO rewardFallback = new RewardFallbackReqDTO();
            rewardFallback.setTaskId(taskId);
            rewardFallback.setStrategyId(strategyId);
            rewardFallback.setUid(goodsSendDTO.getUid());
            rewardFallback.setRewardStatus(2);
            rewardFallback.setRewardTime(new Date());
            rewardFallback.setFailReason(e.getMessage());
            taskRemote.rewardFallback(rewardFallback);
        }
    }

    private void checkPayCallbackParam(PayCallbackDTO payCallbackDTO) {
        if (StringUtils.isBlank(payCallbackDTO.getOrderNo())) {
            throw new GdngException(GlobalResponseEnum.BIZ_PARAM_ERR, "支付回调订单编号不能为空");
        }
        if (payCallbackDTO.getPayWay() == null) {
            throw new GdngException(GlobalResponseEnum.BIZ_PARAM_ERR, "支付回调支付方式不能为空");
        }
        if (payCallbackDTO.getPayTime() == null) {
            throw new GdngException(GlobalResponseEnum.BIZ_PARAM_ERR, "支付回调支付时间不能为空");
        }
        if (StringUtils.isBlank(payCallbackDTO.getPayOrderNo())) {
            throw new GdngException(GlobalResponseEnum.BIZ_PARAM_ERR, "支付回调支付单号不能为空");
        }
        if (StringUtils.isBlank(payCallbackDTO.getPayerUid())) {
            throw new GdngException(GlobalResponseEnum.BIZ_PARAM_ERR, "支付回调付款人不能为空");
        }
    }


}
