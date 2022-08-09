package com.gdng.inner.api.user.invoke;

import com.gdng.inner.api.user.dto.WxUserDTO;
import com.gdng.inner.api.user.fallback.WxUserRemoteFallbackFactory;
import com.gdng.support.common.dto.UserDTO;
import com.gdng.support.common.dto.res.ResDTO;
import com.gdng.support.common.spring.feign.FeignConf;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/8/2 13:39
 * @Description:
 * @Version: 1.0.0
 */
@FeignClient(value="gdng-core-user-procedure",path = "/core/user/wx",fallbackFactory= WxUserRemoteFallbackFactory.class,configuration =
        {FeignConf.class})
public interface WxUserRemote {

    @PostMapping("/login")
    ResDTO<UserDTO> login(@RequestBody WxUserDTO userDTO);

}
