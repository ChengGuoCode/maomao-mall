package com.gdng.support.common.cache.redis;

import com.gdng.support.common.spring.SpringContextHolder;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Map;

public class RedisCache {

    private final String keyPrefix;
    private static volatile RedisTemplate<String, Object> redisTemplate;

    public RedisCache(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }

    public Object get(String key) {
        return getRedisTemplate().opsForValue().get(keyPrefix + key);
    }

    public void set(String key, String val) {
        getRedisTemplate().opsForValue().set(keyPrefix + key, val);
    }

    public Object hget(String key, String hashKey) {
        return getRedisTemplate().opsForHash().get(keyPrefix + key, hashKey);
    }

    public void hset(String key, String hashKey, String hashVal) {
        getRedisTemplate().opsForHash().put(keyPrefix + key, hashKey, hashVal);
    }

    public Map<Object, Object> multiHget(String key) {
        return getRedisTemplate().opsForHash().entries(key);
    }

    public void multiHset(String key, Map<String, String> map) {
        getRedisTemplate().opsForHash().putAll(keyPrefix + key, map);
    }

    @SuppressWarnings("unchecked")
    private RedisTemplate<String, Object> getRedisTemplate() {
        if (redisTemplate == null) {
            synchronized (RedisCache.class) {
                if (redisTemplate == null) {
                    redisTemplate = (RedisTemplate<String, Object>) SpringContextHolder.getBean("redisTemplate");
                }
            }
        }
        return redisTemplate;
    }
}
