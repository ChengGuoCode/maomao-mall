package com.gdng.inner.api.user.invoke;

import com.gdng.inner.api.user.fallback.UserRemoteFallbackFactory;
import com.gdng.support.common.dto.UserDTO;
import com.gdng.support.common.dto.res.ResDTO;
import com.gdng.support.common.spring.feign.FeignConf;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/1 16:44
 * @Description:
 * @Version: 1.0.0
 */
@FeignClient(value="gdng-core-user-procedure",path = "/core/user",fallbackFactory= UserRemoteFallbackFactory.class,configuration =
        {FeignConf.class})
public interface UserRemote {

    @PostMapping("/login")
    ResDTO<UserDTO> login(@RequestBody UserDTO userDTO);

    @PostMapping("/addOrUpdate")
    ResDTO<?> addOrUpdate(@RequestBody UserDTO userDTO);

}
