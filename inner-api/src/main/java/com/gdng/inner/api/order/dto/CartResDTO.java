package com.gdng.inner.api.order.dto;

import java.util.List;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/6 17:12
 * @Description:
 * @Version: 1.0.0
 */
public class CartResDTO {

    private Long businessId;
    private Long storeId;
    private String storeName;
    private List<CartGoodsDTO> goodsList;


    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public List<CartGoodsDTO> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<CartGoodsDTO> goodsList) {
        this.goodsList = goodsList;
    }
}
