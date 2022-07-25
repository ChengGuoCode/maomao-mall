package com.gdng.inner.api.task.dto;

import java.util.Date;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/25 17:50
 * @Description:
 * @Version: 1.0.0
 */
public class RewardFallbackReqDTO {

    private Long taskId;
    private Long strategyId;
    private String uid;
    private Integer rewardStatus;
    private Date rewardTime;
    private String failReason;

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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getRewardStatus() {
        return rewardStatus;
    }

    public void setRewardStatus(Integer rewardStatus) {
        this.rewardStatus = rewardStatus;
    }

    public Date getRewardTime() {
        return rewardTime;
    }

    public void setRewardTime(Date rewardTime) {
        this.rewardTime = rewardTime;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }
}
