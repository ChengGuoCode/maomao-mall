package com.gdng.core.task.controller;

import com.gdng.core.task.service.TaskService;
import com.gdng.inner.api.task.dto.TaskDTO;
import com.gdng.support.common.dto.res.ResDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/getTaskList")
    public ResDTO<?> getTaskList(@RequestParam Integer taskType) {
        return ResDTO.buildSuccessResult(taskService.getTaskList(taskType));
    }

}
