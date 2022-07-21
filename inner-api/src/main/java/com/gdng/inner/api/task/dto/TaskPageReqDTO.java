package com.gdng.inner.api.task.dto;

import com.gdng.support.common.dto.req.PageReqDTO;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/20 16:24
 * @Description:
 * @Version: 1.0.0
 */
public class TaskPageReqDTO extends PageReqDTO {

    private Integer taskType;

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }
}
