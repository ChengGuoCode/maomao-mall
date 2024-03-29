package com.gdng.inner.api.task.dto;

import java.util.Date;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/20 16:20
 * @Description:
 * @Version: 1.0.0
 */
public class TaskResDTO {

    private Long taskId;
    private String name;
    private String conditionDesc;
    private Integer rewardType;
    private Integer rewardPoint;
    private Integer taskStatus;
    private Integer completeStatus;
    private Integer conditionVal;
    private String progress;
    private Date createTime;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConditionDesc() {
        return conditionDesc;
    }

    public void setConditionDesc(String conditionDesc) {
        this.conditionDesc = conditionDesc;
    }

    public Integer getRewardType() {
        return rewardType;
    }

    public void setRewardType(Integer rewardType) {
        this.rewardType = rewardType;
    }

    public Integer getRewardPoint() {
        return rewardPoint;
    }

    public void setRewardPoint(Integer rewardPoint) {
        this.rewardPoint = rewardPoint;
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Integer getCompleteStatus() {
        return completeStatus;
    }

    public void setCompleteStatus(Integer completeStatus) {
        this.completeStatus = completeStatus;
    }

    public Integer getConditionVal() {
        return conditionVal;
    }

    public void setConditionVal(Integer conditionVal) {
        this.conditionVal = conditionVal;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
