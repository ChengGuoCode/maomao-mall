package com.gdng.inner.api.goods.dto;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/6 14:55
 * @Description:
 * @Version: 1.0.0
 */
public class StoreProductSkuResDTO {

    private Long businessId;
    private Long storeId;
    private Long productId;
    private Long skuId;
    private String skuCode;
    private Long storeProductId;
    private Integer stock;
    private Long price;
    private String specification;
    private Long saleVolume;
    private Long hot;
    private boolean selected = false;

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

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public Long getStoreProductId() {
        return storeProductId;
    }

    public void setStoreProductId(Long storeProductId) {
        this.storeProductId = storeProductId;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public Long getSaleVolume() {
        return saleVolume;
    }

    public void setSaleVolume(Long saleVolume) {
        this.saleVolume = saleVolume;
    }

    public Long getHot() {
        return hot;
    }

    public void setHot(Long hot) {
        this.hot = hot;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
