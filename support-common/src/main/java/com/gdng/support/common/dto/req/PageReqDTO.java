package com.gdng.support.common.dto.req;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/1 16:54
 * @Description:
 * @Version: 1.0.0
 */
public class PageReqDTO {

    private int pageNo = 1;
    private int pageSize = 20;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
