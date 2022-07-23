package com.gdng.inner.api.task.dto;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/23 15:58
 * @Description:
 * @Version: 1.0.0
 */
public class TaskExecuteReqDTO {

    private Long taskId;
    private String uid;
    private Integer times;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }
}
