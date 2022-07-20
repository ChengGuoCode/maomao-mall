package com.gdng.core.task.service;

import com.gdng.inner.api.task.dto.TaskDTO;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/20 10:11
 * @Description:
 * @Version: 1.0.0
 */
public interface TaskService {

    void addOrUpdate(TaskDTO taskDTO);

    int getTaskList(Integer taskType);

}
