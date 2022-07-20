package com.gdng.inner.api.task.dto;

import java.util.List;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/20 10:19
 * @Description:
 * @Version: 1.0.0
 */
public class TaskStrategyDTO {

    private Long id;
    private Integer conditionVal;
    private String conditionDesc;
    private Integer rewardType;
    private Integer rewardPoint;
    private String intraStartTime;
    private String intraEndTime;
    private List<TaskPrizeDTO> prizeList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getConditionVal() {
        return conditionVal;
    }

    public void setConditionVal(Integer conditionVal) {
        this.conditionVal = conditionVal;
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

    public String getIntraStartTime() {
        return intraStartTime;
    }

    public void setIntraStartTime(String intraStartTime) {
        this.intraStartTime = intraStartTime;
    }

    public String getIntraEndTime() {
        return intraEndTime;
    }

    public void setIntraEndTime(String intraEndTime) {
        this.intraEndTime = intraEndTime;
    }

    public List<TaskPrizeDTO> getPrizeList() {
        return prizeList;
    }

    public void setPrizeList(List<TaskPrizeDTO> prizeList) {
        this.prizeList = prizeList;
    }
}
