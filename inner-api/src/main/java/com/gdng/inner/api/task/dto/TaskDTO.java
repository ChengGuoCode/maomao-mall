package com.gdng.inner.api.task.dto;

import java.util.Date;
import java.util.List;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/20 10:17
 * @Description:
 * @Version: 1.0.0
 */
public class TaskDTO {

    private Long id;
    private String name;
    private Integer rewardStrategy;
    private Integer limitTimes;
    private Date startTime;
    private Date endTime;
    private List<TaskStrategyDTO> strategyList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRewardStrategy() {
        return rewardStrategy;
    }

    public void setRewardStrategy(Integer rewardStrategy) {
        this.rewardStrategy = rewardStrategy;
    }

    public Integer getLimitTimes() {
        return limitTimes;
    }

    public void setLimitTimes(Integer limitTimes) {
        this.limitTimes = limitTimes;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public List<TaskStrategyDTO> getStrategyList() {
        return strategyList;
    }

    public void setStrategyList(List<TaskStrategyDTO> strategyList) {
        this.strategyList = strategyList;
    }
}
