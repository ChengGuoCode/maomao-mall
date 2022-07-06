package com.gdng.inner.api.goods.dto;

import java.util.List;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/6 14:48
 * @Description:
 * @Version: 1.0.0
 */
public class StoreProductResDTO {

    private Long businessId;
    private Long storeId;
    private Long productId;
    private String productCode;
    private Long categoryId;
    private String categoryName;
    private Integer productType;
    private String productTypeStr;
    private Long brandId;
    private String brandName;
    private String alias;
    private Long price;
    private String pic;
    private String label;
    private String productDesc;
    private String useExplain;
    private Integer stock;
    private Long saleVolume;
    private Long hot;
    private Integer returnPolicy;
    private Integer exchangePolicy;
    private List<StoreProductSkuResDTO> skuList;

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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public String getProductTypeStr() {
        return productTypeStr;
    }

    public void setProductTypeStr(String productTypeStr) {
        this.productTypeStr = productTypeStr;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
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

    public List<StoreProductSkuResDTO> getSkuList() {
        return skuList;
    }

    public void setSkuList(List<StoreProductSkuResDTO> skuList) {
        this.skuList = skuList;
    }
}
