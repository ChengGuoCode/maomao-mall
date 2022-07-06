package com.gdng.inner.api.goods.dto;

import com.gdng.support.common.dto.req.PageReqDTO;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/5 10:29
 * @Description:
 * @Version: 1.0.0
 */
public class StoreProductReqDTO extends PageReqDTO {

    private String productName;
    private Long categoryId;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
