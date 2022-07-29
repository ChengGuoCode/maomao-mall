package com.gdng.core.user.common;

import com.gdng.core.user.service.UserService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/29 16:42
 * @Description:
 * @Version: 1.0.0
 */
@Component
public class UserContextHolder implements InitializingBean {

    @Autowired
    private UserService userService;

    @Override
    public void afterPropertiesSet() throws Exception {
        userService.initRolePermissionCache();
    }
}
