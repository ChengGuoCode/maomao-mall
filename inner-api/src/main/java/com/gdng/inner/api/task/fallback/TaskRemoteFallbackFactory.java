package com.gdng.inner.api.task.fallback;

import com.gdng.inner.api.task.dto.*;
import com.gdng.inner.api.task.invoke.TaskRemote;
import com.gdng.support.common.dto.res.PageResDTO;
import com.gdng.support.common.dto.res.ResDTO;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/23 15:55
 * @Description:
 * @Version: 1.0.0
 */
@Component
public class TaskRemoteFallbackFactory implements FallbackFactory<TaskRemote> {
    @Override
    public TaskRemote create(Throwable cause) {
        return new TaskRemote() {
            @Override
            public ResDTO<?> addOrUpdate(TaskDTO taskDTO) {
                return ResDTO.buildBusyResult();
            }

            @Override
            public ResDTO<PageResDTO<TaskResDTO>> getTaskList(TaskPageReqDTO reqDTO) {
                return ResDTO.buildBusyResult();
            }

            @Override
            public ResDTO<?> execute(TaskExecuteReqDTO reqDTO) {
                return ResDTO.buildBusyResult();
            }

            @Override
            public ResDTO<?> rewardFallback(RewardFallbackReqDTO reqDTO) {
                return ResDTO.buildBusyResult();
            }

            @Override
            public ResDTO<?> receivePrize(RewardReceiveReqDTO reqDTO) {
                return ResDTO.buildBusyResult();
            }
        };
    }
}
