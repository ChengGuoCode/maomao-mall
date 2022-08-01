package com.gdng.support.common.cache.redis.user;

import com.gdng.support.common.cache.redis.RedisCache;
import com.gdng.support.common.dto.UserDTO;
import com.gdng.support.common.util.JacksonUtil;

import java.util.HashMap;
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

    public static List<String> getUserRolePermission(String roleName) {
        Object permissionUrlObj = USER_ROLE_PERMISSION.hget("rid", roleName);
        return JacksonUtil.jsonToBean(permissionUrlObj.toString(), List.class);
    }

    public static void setUserRolePermission(String roleName, List<String> permissionUrlList) {
        USER_ROLE_PERMISSION.hset("rid", roleName, JacksonUtil.anyToJson(permissionUrlList));
    }

    public static void multiSetUserRolePermission(Map<String, String> rolePermissionMap) {
        USER_ROLE_PERMISSION.multiHset("rid", rolePermissionMap);
    }

    public static Map<String, List<String>> multiGetUserRolePermission() {
        Map<Object, Object> ridObjMap = USER_ROLE_PERMISSION.multiHget("rid");
        Map<String, List<String>> ridMap = new HashMap<>();
        ridObjMap.forEach((x, y) -> {
            ridMap.put(x.toString(), JacksonUtil.jsonToBean(y.toString(), List.class));
        });
        return ridMap;
    }

}
