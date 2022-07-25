package com.gdng.inner.api.task.dto.mq;

import java.io.Serializable;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/25 16:09
 * @Description:
 * @Version: 1.0.0
 */
public class GoodsSendItemDTO implements Serializable {

    private Long businessId;
    private Long storeId;
    private Long productId;
    private String skuCode;
    private Integer sendNum;

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

    public Integer getSendNum() {
        return sendNum;
    }

    public void setSendNum(Integer sendNum) {
        this.sendNum = sendNum;
    }
}
