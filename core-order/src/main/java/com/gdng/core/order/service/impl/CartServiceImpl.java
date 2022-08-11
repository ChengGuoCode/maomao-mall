package com.gdng.core.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.gdng.core.order.dao.service.OrderCartDaoService;
import com.gdng.core.order.service.CartService;
import com.gdng.entity.order.po.OrderCartPO;
import com.gdng.inner.api.goods.dto.StoreProductSkuStockDTO;
import com.gdng.inner.api.goods.dto.StoreProductSkuStockResDTO;
import com.gdng.inner.api.goods.invoke.StoreProductRemote;
import com.gdng.inner.api.order.dto.CartGoodsDTO;
import com.gdng.inner.api.order.dto.CartReqDTO;
import com.gdng.inner.api.order.dto.CartResDTO;
import com.gdng.support.common.cache.redis.goods.GoodsRedisCache;
import com.gdng.support.common.cache.redis.goods.dto.StoreProductDTO;
import com.gdng.support.common.cache.redis.goods.dto.StoreProductSkuDTO;
import com.gdng.support.common.constant.StringConstant;
import com.gdng.support.common.dto.res.GlobalResponseEnum;
import com.gdng.support.common.dto.res.ResDTO;
import com.gdng.support.common.exception.GdngException;
import com.gdng.support.common.util.GdngBeanUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.gdng.support.common.constant.StringConstant.POUND;

@Service
public class CartServiceImpl implements CartService {

    private final OrderCartDaoService cartDaoService;
    private final StoreProductRemote storeProductRemote;

    @Autowired
    public CartServiceImpl(OrderCartDaoService cartDaoService, StoreProductRemote storeProductRemote) {
        this.cartDaoService = cartDaoService;
        this.storeProductRemote = storeProductRemote;
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
        } else {
            cartPO.setNum(cartPO.getNum() + num);
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
            List<StoreProductSkuStockDTO> storeProductSkuStockList = cartPOList.stream().map(cartPO -> GdngBeanUtil.copyToNewBean(cartPO, StoreProductSkuStockDTO.class)).collect(Collectors.toList());
            ResDTO<List<StoreProductSkuStockResDTO>> storeProductSkuStockRes =
                    storeProductRemote.getStoreProductSkuStock(storeProductSkuStockList);
            if (!storeProductSkuStockRes.isSuccess()) {
                throw new GdngException(GlobalResponseEnum.REMOTE_ERR, "StoreProductRemote.getStoreProductSkuStock");
            }
            List<StoreProductSkuStockResDTO> storeProductSkuStockResData = storeProductSkuStockRes.getData();
            Map<String, Integer> productSkuStockMap = new HashMap<>();
            if (CollectionUtils.isNotEmpty(storeProductSkuStockResData)) {
                storeProductSkuStockResData.forEach(storeProductSkuStock -> {
                    Long businessId = storeProductSkuStock.getBusinessId();
                    Long storeId = storeProductSkuStock.getStoreId();
                    Long productId = storeProductSkuStock.getProductId();
                    String skuCode = storeProductSkuStock.getSkuCode();
                    Integer stock = storeProductSkuStock.getStock();
                    productSkuStockMap.put(businessId + POUND + storeId + POUND + productId + POUND + skuCode, stock);
                });
            }
            Map<String, CartResDTO> cartResMap = new HashMap<>();
            cartPOList.forEach(cartPO -> {
                Long businessId = cartPO.getBusinessId();
                Long storeId = cartPO.getStoreId();
                String storeName = cartPO.getStoreName();
                Long productId = cartPO.getProductId();
                String skuCode = cartPO.getSkuCode();
                Integer num = cartPO.getNum();
                String key = businessId + POUND + storeId;
                String skuKey = businessId + POUND + storeId + POUND + productId + POUND + skuCode;
                CartResDTO cartResDTO = cartResMap.get(key);
                Integer stock = productSkuStockMap.get(skuKey);
                if (cartResDTO == null) {
                    cartResDTO = new CartResDTO();
                    cartResDTO.setBusinessId(businessId);
                    cartResDTO.setStoreId(storeId);
                    cartResDTO.setStoreName(storeName);
                    List<CartGoodsDTO> goodsList = new ArrayList<>();
                    CartGoodsDTO cartGoodsDTO = new CartGoodsDTO();
                    cartGoodsDTO.setProductId(productId);
                    cartGoodsDTO.setSkuCode(skuCode);
                    cartGoodsDTO.setNum(num);
                    cartGoodsDTO.setStock(stock == null ? 0 : stock);
                    StoreProductDTO storeProduct = GoodsRedisCache.getStoreProduct(businessId, storeId, productId);
                    if (storeProduct != null) {
                        cartGoodsDTO.setProductName(storeProduct.getAlias());
                        cartGoodsDTO.setProductImg(storeProduct.getPic());
                        cartGoodsDTO.setProductDesc(storeProduct.getProductDesc());
                        StoreProductSkuDTO storeProductSku = GoodsRedisCache.getStoreProductSku(businessId, storeId, productId, skuCode);
                        if (storeProductSku != null) {
                            cartGoodsDTO.setSpecification(storeProductSku.getSpecification());
                            cartGoodsDTO.setPrice(storeProductSku.getPrice());
                        }
                    }
                    goodsList.add(cartGoodsDTO);
                    cartResDTO.setGoodsList(goodsList);
                    cartResMap.put(key, cartResDTO);
                } else {
                    List<CartGoodsDTO> goodsList = cartResDTO.getGoodsList();
                    CartGoodsDTO cartGoodsDTO = new CartGoodsDTO();
                    cartGoodsDTO.setProductId(productId);
                    cartGoodsDTO.setSkuCode(skuCode);
                    cartGoodsDTO.setNum(num);
                    cartGoodsDTO.setStock(stock == null ? 0 : stock);
                    StoreProductDTO storeProduct = GoodsRedisCache.getStoreProduct(businessId, storeId, productId);
                    if (storeProduct != null) {
                        cartGoodsDTO.setProductName(storeProduct.getAlias());
                        cartGoodsDTO.setProductImg(storeProduct.getPic());
                        cartGoodsDTO.setProductDesc(storeProduct.getProductDesc());
                        StoreProductSkuDTO storeProductSku = GoodsRedisCache.getStoreProductSku(businessId, storeId, productId, skuCode);
                        if (storeProductSku != null) {
                            cartGoodsDTO.setSpecification(storeProductSku.getSpecification());
                            cartGoodsDTO.setPrice(storeProductSku.getPrice());
                        }
                    }
                    goodsList.add(cartGoodsDTO);
                }
            });
            cartResDTOList.addAll(cartResMap.values());
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
