package com.gdng.inner.api.order.dto;

import com.gdng.support.common.dto.req.PageReqDTO;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/5 14:22
 * @Description:
 * @Version: 1.0.0
 */
public class ShareReqDTO extends PageReqDTO {

    private Long categoryId;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
