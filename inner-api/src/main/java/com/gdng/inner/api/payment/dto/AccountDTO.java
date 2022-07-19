package com.gdng.inner.api.payment.dto;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/19 11:20
 * @Description:
 * @Version: 1.0.0
 */
public class AccountDTO {

    private Long id;
    private Integer type;
    private String corelationId;
    private String payPass;
    private Integer accStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCorelationId() {
        return corelationId;
    }

    public void setCorelationId(String corelationId) {
        this.corelationId = corelationId;
    }

    public String getPayPass() {
        return payPass;
    }

    public void setPayPass(String payPass) {
        this.payPass = payPass;
    }

    public Integer getAccStatus() {
        return accStatus;
    }

    public void setAccStatus(Integer accStatus) {
        this.accStatus = accStatus;
    }
}
