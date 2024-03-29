package com.gdng.inner.api.goods.dto;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/8/11 10:24
 * @Description:
 * @Version: 1.0.0
 */
public class StoreProductCacheDTO {

    private Long businessId;
    private Long storeId;
    private Long productId;
    private String skuCode;

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
}
