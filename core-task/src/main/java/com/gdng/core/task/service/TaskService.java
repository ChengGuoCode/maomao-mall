package com.gdng.core.task.service;

import com.gdng.inner.api.task.dto.TaskDTO;
import com.gdng.inner.api.task.dto.TaskExecuteReqDTO;
import com.gdng.inner.api.task.dto.TaskPageReqDTO;
import com.gdng.inner.api.task.dto.TaskResDTO;
import com.gdng.support.common.dto.res.PageResDTO;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/20 10:11
 * @Description:
 * @Version: 1.0.0
 */
public interface TaskService {

    void addOrUpdate(TaskDTO taskDTO);

    PageResDTO<TaskResDTO> getTaskList(TaskPageReqDTO reqDTO);

    void execute(TaskExecuteReqDTO reqDTO);

}
