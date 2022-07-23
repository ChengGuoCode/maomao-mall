package com.gdng.inner.api.task.invoke;

import com.gdng.inner.api.task.dto.TaskDTO;
import com.gdng.inner.api.task.dto.TaskExecuteReqDTO;
import com.gdng.inner.api.task.dto.TaskPageReqDTO;
import com.gdng.inner.api.task.dto.TaskResDTO;
import com.gdng.inner.api.task.fallback.TaskRemoteFallbackFactory;
import com.gdng.support.common.dto.res.PageResDTO;
import com.gdng.support.common.dto.res.ResDTO;
import com.gdng.support.common.spring.feign.FeignConf;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/23 15:53
 * @Description:
 * @Version: 1.0.0
 */
@FeignClient(value="gdng-core-task-procedure",path = "/core/task",fallbackFactory=
        TaskRemoteFallbackFactory.class,
        configuration = {FeignConf.class})
public interface TaskRemote {

    @PostMapping("/addOrUpdate")
    ResDTO<?> addOrUpdate(@RequestBody TaskDTO taskDTO);

    @PostMapping("/getTaskList")
    ResDTO<PageResDTO<TaskResDTO>> getTaskList(@RequestBody TaskPageReqDTO reqDTO);

    @PostMapping("/execute")
    ResDTO<?> execute(@RequestBody TaskExecuteReqDTO reqDTO);

}
