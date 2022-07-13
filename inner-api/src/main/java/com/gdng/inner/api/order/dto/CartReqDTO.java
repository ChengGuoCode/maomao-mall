package com.gdng.inner.api.order.dto;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/5 10:59
 * @Description:
 * @Version: 1.0.0
 */
public class CartReqDTO {

    private String uid;
    private Long businessId;
    private Long storeId;
    private Long productId;
    private String skuCode;
    private Integer num;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
