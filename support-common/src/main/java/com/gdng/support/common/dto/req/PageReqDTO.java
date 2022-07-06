package com.gdng.support.common.dto.req;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/1 16:54
 * @Description:
 * @Version: 1.0.0
 */
public class PageReqDTO {

    private long pageNo = 1;
    private long pageSize = 20;

    public long getPageNo() {
        return pageNo;
    }

    public void setPageNo(long pageNo) {
        this.pageNo = pageNo;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }
}
