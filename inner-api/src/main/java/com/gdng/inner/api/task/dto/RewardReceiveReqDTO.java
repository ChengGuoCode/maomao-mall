package com.gdng.inner.api.task.dto;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/27 09:58
 * @Description:
 * @Version: 1.0.0
 */
public class RewardReceiveReqDTO {

    private Long taskId;
    private Long strategyId;
    private String uid;

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
}
