package com.gdng.support.common.dto.res;

import java.util.List;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/5 10:42
 * @Description:
 * @Version: 1.0.0
 */
public class PageResDTO<T> {

    private int pageNo;
    private int pageSize;
    private long total;
    private List<T> records;

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

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }
}
