package com.gdng.support.common.cache.redis.user;

import com.gdng.support.common.cache.redis.RedisCache;
import com.gdng.support.common.dto.UserDTO;
import com.gdng.support.common.util.JacksonUtil;

import java.util.List;
import java.util.Map;

public class UserRedisCache {

    private static final RedisCache USER_AUTH_CACHE = new RedisCache("user:auth:");
    private static final RedisCache USER_CACHE = new RedisCache("user:info:");
    private static final RedisCache USER_ROLE_PERMISSION = new RedisCache("user:role_permission:");

    public static String getAsyCryptKeyPair(String algorithm) {
        return String.valueOf(USER_AUTH_CACHE.get(algorithm));
    }

    public static void setAsyCryptKeyPair(String algorithm, String keyPair) {
        USER_AUTH_CACHE.set(algorithm, keyPair);
    }

    public static UserDTO getUserInfoByUid(String uid) {
        Object userInfo = USER_CACHE.hget("uid", uid);
        return userInfo == null ? null : JacksonUtil.jsonToBean(userInfo.toString(), UserDTO.class);
    }

    public static void setUserInfo(UserDTO userInfo) {
        USER_CACHE.hset("uid", userInfo.getId(), JacksonUtil.anyToJson(userInfo));
    }

    public static void setUserRolePermission(Map<String, Object> rolePermissionMap) {
        USER_ROLE_PERMISSION.multiSet("rid", rolePermissionMap);
    }

}
