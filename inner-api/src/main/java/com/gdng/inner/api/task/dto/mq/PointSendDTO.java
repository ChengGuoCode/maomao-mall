package com.gdng.inner.api.task.dto.mq;

import java.io.Serializable;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/25 15:48
 * @Description:
 * @Version: 1.0.0
 */
public class PointSendDTO implements Serializable {

    private Long taskId;
    private Long strategyId;
    private String payUid;
    private String beneficiaryUid;
    private Integer point;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }

    public String getPayUid() {
        return payUid;
    }

    public void setPayUid(String payUid) {
        this.payUid = payUid;
    }

    public String getBeneficiaryUid() {
        return beneficiaryUid;
    }

    public void setBeneficiaryUid(String beneficiaryUid) {
        this.beneficiaryUid = beneficiaryUid;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }
}
