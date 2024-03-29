package com.gdng.core.user.common;

import com.gdng.core.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/29 16:42
 * @Description:
 * @Version: 1.0.0
 */
@Component
@DependsOn({"redisTemplate", "springContextHolder"})
public class PermissionCacheInitHandler {

    @Autowired
    private UserService userService;

    @PostConstruct
    public void initPermissionCache() {
        userService.syncRolePermissionCache();
    }
}
