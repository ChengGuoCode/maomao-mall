package com.gdng.inner.api.payment.dto;

import java.util.List;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/8 16:35
 * @Description:
 * @Version: 1.0.0
 */
public class OrderPayReqDTO {

    private String orderNo;
    private Long payment;
    private Integer payWay;
    private String payerUid;
    private List<OrderPayItemDTO> orderPayItemList;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Long getPayment() {
        return payment;
    }

    public void setPayment(Long payment) {
        this.payment = payment;
    }

    public Integer getPayWay() {
        return payWay;
    }

    public void setPayWay(Integer payWay) {
        this.payWay = payWay;
    }

    public String getPayerUid() {
        return payerUid;
    }

    public void setPayerUid(String payerUid) {
        this.payerUid = payerUid;
    }

    public List<OrderPayItemDTO> getOrderPayItemList() {
        return orderPayItemList;
    }

    public void setOrderPayItemList(List<OrderPayItemDTO> orderPayItemList) {
        this.orderPayItemList = orderPayItemList;
    }
}
