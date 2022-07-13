package com.gdng.core.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.gdng.core.order.dao.service.OrderCartDaoService;
import com.gdng.core.order.service.CartService;
import com.gdng.entity.order.po.OrderCartPO;
import com.gdng.inner.api.order.dto.CartReqDTO;
import com.gdng.inner.api.order.dto.CartResDTO;
import com.gdng.support.common.dto.res.GlobalResponseEnum;
import com.gdng.support.common.exception.GdngException;
import com.gdng.support.common.util.GdngBeanUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    private final OrderCartDaoService cartDaoService;

    @Autowired
    public CartServiceImpl(OrderCartDaoService cartDaoService) {
        this.cartDaoService = cartDaoService;
    }

    @Override
    public void addOrUpdateProduct(CartReqDTO reqDTO) {
        checkCartParam(reqDTO);
        String uid = reqDTO.getUid();
        Long businessId = reqDTO.getBusinessId();
        Long storeId = reqDTO.getStoreId();
        Long productId = reqDTO.getProductId();
        String skuCode = reqDTO.getSkuCode();
        Integer num = reqDTO.getNum();
        if (num == null || num < 1) {
            throw new GdngException(GlobalResponseEnum.BIZ_PARAM_ERR, "商品加入购物车数量应大于等于1");
        }

        OrderCartPO cartPO = cartDaoService.getOne(new QueryWrapper<OrderCartPO>().eq("uid", uid).eq("business_id", businessId).eq(
                "store_id", storeId).eq("product_id", productId).eq("sku_code", skuCode));
        if (cartPO == null) {
            OrderCartPO orderCartPO = new OrderCartPO();
            orderCartPO.setUid(uid);
            orderCartPO.setBusinessId(businessId);
            orderCartPO.setStoreId(storeId);
            orderCartPO.setStoreName("");
            orderCartPO.setProductId(productId);
            orderCartPO.setSkuCode(skuCode);
            orderCartPO.setNum(num);
            cartDaoService.save(orderCartPO);
        } else if (!num.equals(cartPO.getNum())) {
            cartPO.setNum(num);
            cartDaoService.updateById(cartPO);
        }
    }

    @Override
    public void removeProduct(CartReqDTO reqDTO) {
        checkCartParam(reqDTO);
        String uid = reqDTO.getUid();
        Long businessId = reqDTO.getBusinessId();
        Long storeId = reqDTO.getStoreId();
        Long productId = reqDTO.getProductId();
        String skuCode = reqDTO.getSkuCode();

        cartDaoService.remove(new QueryWrapper<OrderCartPO>().eq("uid", uid).eq("business_id", businessId).eq(
                "store_id", storeId).eq("product_id", productId).eq("sku_code", skuCode));
    }

    @Override
    public List<CartResDTO> getCartList(String uid) {
        List<OrderCartPO> cartPOList = cartDaoService.list(new QueryWrapper<OrderCartPO>().eq("uid", uid).orderByDesc("create_time"));
        List<CartResDTO> cartResDTOList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(cartPOList)) {
            cartResDTOList.addAll(cartPOList.stream().map(cartPO -> {
                CartResDTO cartResDTO = GdngBeanUtil.copyToNewBean(cartPO, CartResDTO.class);
                return cartResDTO;
            }).collect(Collectors.toList()));
        }
        return cartResDTOList;
    }

    private void checkCartParam(CartReqDTO reqDTO) {
        if (StringUtils.isBlank(reqDTO.getUid())) {
            throw new GdngException(GlobalResponseEnum.BIZ_PARAM_ERR, "用户ID不能为空");
        }
        if (reqDTO.getBusinessId() == null || reqDTO.getStoreId() == null || reqDTO.getProductId() == null) {
            throw new GdngException(GlobalResponseEnum.BIZ_PARAM_ERR, "商品信息不能为空");
        }
        if (StringUtils.isBlank(reqDTO.getSkuCode())) {
            throw new GdngException(GlobalResponseEnum.BIZ_PARAM_ERR, "商品规格不能为空");
        }
    }
}
