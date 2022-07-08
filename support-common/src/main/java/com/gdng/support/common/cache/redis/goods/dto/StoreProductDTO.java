package com.gdng.support.common.cache.redis.goods.dto;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/8 10:35
 * @Description:
 * @Version: 1.0.0
 */
public class StoreProductDTO {

    private Long businessId;
    private Long storeId;
    private Long productId;
    private String productCode;
    private Long categoryId;
    private Integer productType;
    private Long brandId;
    private String alias;
    private Long price;
    private String pic;
    private String label;
    private String productDesc;
    private String useExplain;
    private Integer stock;
    private Integer weight;
    private Integer saleStatus;
    private Integer returnPolicy;
    private Integer exchangePolicy;

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

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getUseExplain() {
        return useExplain;
    }

    public void setUseExplain(String useExplain) {
        this.useExplain = useExplain;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(Integer saleStatus) {
        this.saleStatus = saleStatus;
    }

    public Integer getReturnPolicy() {
        return returnPolicy;
    }

    public void setReturnPolicy(Integer returnPolicy) {
        this.returnPolicy = returnPolicy;
    }

    public Integer getExchangePolicy() {
        return exchangePolicy;
    }

    public void setExchangePolicy(Integer exchangePolicy) {
        this.exchangePolicy = exchangePolicy;
    }
}
