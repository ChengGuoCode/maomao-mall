package com.gdng.inner.api.payment.dto.mq;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/12 18:00
 * @Description:
 * @Version: 1.0.0
 */
public class PayCallbackDTO implements Serializable {

    private String orderNo;
    private Integer payWay;
    private Date payTime;
    private String payOrderNo;
    private String payerUid;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getPayWay() {
        return payWay;
    }

    public void setPayWay(Integer payWay) {
        this.payWay = payWay;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getPayOrderNo() {
        return payOrderNo;
    }

    public void setPayOrderNo(String payOrderNo) {
        this.payOrderNo = payOrderNo;
    }

    public String getPayerUid() {
        return payerUid;
    }

    public void setPayerUid(String payerUid) {
        this.payerUid = payerUid;
    }
}
