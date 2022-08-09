package com.gdng.core.user.service;

import com.gdng.inner.api.user.dto.WxUserDTO;
import com.gdng.support.common.dto.UserDTO;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/8/2 10:28
 * @Description:
 * @Version: 1.0.0
 */
public interface WxUserService {

    UserDTO login(WxUserDTO userDTO);

}
