package com.gdng.inner.api.user.fallback;

import com.gdng.inner.api.user.dto.WxUserDTO;
import com.gdng.inner.api.user.invoke.WxUserRemote;
import com.gdng.support.common.dto.UserDTO;
import com.gdng.support.common.dto.res.ResDTO;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/8/2 13:40
 * @Description:
 * @Version: 1.0.0
 */
@Component
public class WxUserRemoteFallbackFactory implements FallbackFactory<WxUserRemote> {
    @Override
    public WxUserRemote create(Throwable cause) {
        return new WxUserRemote() {
            @Override
            public ResDTO<UserDTO> login(WxUserDTO userDTO) {
                return ResDTO.buildBusyResult();
            }
        };
    }
}
