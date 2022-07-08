package com.gdng.inner.api.order.dto;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/8 16:21
 * @Description:
 * @Version: 1.0.0
 */
public class OrderCloseReqDTO {

    private String orderNo;
    private String closeReason;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getCloseReason() {
        return closeReason;
    }

    public void setCloseReason(String closeReason) {
        this.closeReason = closeReason;
    }
}
