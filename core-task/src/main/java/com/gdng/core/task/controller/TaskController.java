package com.gdng.core.task.controller;

import com.gdng.core.task.service.TaskService;
import com.gdng.inner.api.task.dto.TaskDTO;
import com.gdng.inner.api.task.dto.TaskExecuteReqDTO;
import com.gdng.inner.api.task.dto.TaskPageReqDTO;
import com.gdng.inner.api.task.dto.TaskResDTO;
import com.gdng.support.common.dto.res.PageResDTO;
import com.gdng.support.common.dto.res.ResDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/20 10:13
 * @Description:
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("core/task")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/addOrUpdate")
    public ResDTO<?> addOrUpdate(@RequestBody TaskDTO taskDTO) {
        taskService.addOrUpdate(taskDTO);
        return ResDTO.buildSuccessResult();
    }

    @PostMapping("/getTaskList")
    public ResDTO<PageResDTO<TaskResDTO>> getTaskList(@RequestBody TaskPageReqDTO reqDTO) {
        return ResDTO.buildSuccessResult(taskService.getTaskList(reqDTO));
    }

    @PostMapping("/execute")
    public ResDTO<?> execute(@RequestBody TaskExecuteReqDTO reqDTO) {
        taskService.execute(reqDTO);
        return ResDTO.buildSuccessResult();
    }

}
