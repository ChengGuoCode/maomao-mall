package com.gdng.service.app.controller;

import com.gdng.inner.api.task.dto.*;
import com.gdng.inner.api.task.invoke.TaskRemote;
import com.gdng.support.common.dto.res.PageResDTO;
import com.gdng.support.common.dto.res.ResDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/5 14:26
 * @Description:
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/service/app/task")
public class TaskController {

    private final TaskRemote taskRemote;

    @Autowired
    public TaskController(TaskRemote taskRemote) {
        this.taskRemote = taskRemote;
    }

    @PostMapping("/addOrUpdate")
    public ResDTO<?> addOrUpdate(@RequestBody TaskDTO taskDTO) {
        return taskRemote.addOrUpdate(taskDTO);
    }

    @PostMapping("/getTaskList")
    public ResDTO<PageResDTO<TaskResDTO>> getTaskList(@RequestBody TaskPageReqDTO reqDTO) {
        return taskRemote.getTaskList(reqDTO);
    }

    @PostMapping("/execute")
    public ResDTO<?> execute(@RequestBody TaskExecuteReqDTO reqDTO) {
        return taskRemote.execute(reqDTO);
    }

    @PostMapping("/receivePrize")
    public ResDTO<?> receivePrize(@RequestBody RewardReceiveReqDTO reqDTO) {
        return taskRemote.receivePrize(reqDTO);
    }

}
