package com.gdng.inner.api.order.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/5 10:59
 * @Description:
 * @Version: 1.0.0
 */
public class CartReqDTO {

    @NotBlank(message = "用户ID不能为空")
    private String uid;
    @NotNull(message = "商家ID不能为空")
    private Long businessId;
    @NotNull(message = "店铺ID不能为空")
    private Long storeId;
    @NotNull(message = "商品ID不能为空")
    private Long productId;
    @NotBlank(message = "商品规格不能为空")
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
