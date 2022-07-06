package com.gdng.support.common.dto.res;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/5 10:42
 * @Description:
 * @Version: 1.0.0
 */
public class PageResDTO<T> {

    private long pageNo;
    private long pageSize;
    private long total;
    private List<T> records;

    public static PageResDTO buildEmptyPage(Page page) {
        PageResDTO<?> pageResDTO = new PageResDTO<>();
        pageResDTO.setPageNo(page.getCurrent());
        pageResDTO.setPageSize(page.getSize());
        pageResDTO.setTotal(page.getTotal());
        return pageResDTO;
    }

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
