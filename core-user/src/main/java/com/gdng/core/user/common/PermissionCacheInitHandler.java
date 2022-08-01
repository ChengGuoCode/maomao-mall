package com.gdng.core.user.common;

import com.gdng.core.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/29 16:42
 * @Description:
 * @Version: 1.0.0
 */
@Component
@DependsOn("redisTemplate")
public class PermissionCacheInitHandler {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @PostConstruct
    public void initPermissionCache() {
        Map<String, String> rolePermissionCache = userService.getRolePermissionCache();
        redisTemplate.opsForHash().putAll("user:role_permission:rid", rolePermissionCache);
    }
}
