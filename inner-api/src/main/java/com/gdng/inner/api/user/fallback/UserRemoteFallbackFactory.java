package com.gdng.inner.api.user.fallback;

import com.gdng.inner.api.user.invoke.UserRemote;
import com.gdng.support.common.dto.UserDTO;
import com.gdng.support.common.dto.res.ResDTO;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/1 16:49
 * @Description:
 * @Version: 1.0.0
 */
@Component
public class UserRemoteFallbackFactory implements FallbackFactory<UserRemote> {
    @Override
    public UserRemote create(Throwable cause) {
        return new UserRemote() {

            @Override
            public ResDTO<UserDTO> login(UserDTO userDTO) {
                return ResDTO.buildBusyResult();
            }

            @Override
            public ResDTO<UserDTO> addOrUpdate(UserDTO userDTO) {
                return ResDTO.buildBusyResult();
            }
        };
    }
}
