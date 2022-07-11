package com.gdng.support.common.lock;

import com.gdng.support.common.cache.redis.RedisCache;
import com.gdng.support.common.dto.res.GlobalResponseEnum;
import com.gdng.support.common.exception.GdngException;
import com.gdng.support.common.spring.SpringContextHolder;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/11 17:29
 * @Description:
 * @Version: 1.0.0
 */
public class RedisLock {

    private static final int LOCK_EXPIRE = 3000;

    private static volatile RedisTemplate<String, Object> redisTemplate;

    private final String prefix;

    private String lockKey;

    public RedisLock(String prefix) {
        this.prefix = prefix;
    }

    public boolean lock(String key) {
        return lock(key, LOCK_EXPIRE, TimeUnit.MILLISECONDS);
    }

    public boolean lock(String lock, long timeout, TimeUnit unit) {
        lockKey = lock;
        switch (unit) {
            case MILLISECONDS:
                break;
            case SECONDS:
                timeout = timeout * 1000;
                break;
            case MINUTES:
                timeout = timeout * 1000 * 60;
                break;
            case HOURS:
                timeout = timeout * 1000 * 60 * 60;
                break;
            case DAYS:
                timeout = timeout * 1000 * 60 * 60 * 24;
                break;
            default:
                throw new GdngException(GlobalResponseEnum.PARAM_ERR, "无效的时间单位");
        }
        final long finalTimeout = timeout;
        final String finalLock = prefix + lock;
        return Boolean.TRUE.equals(getRedisTemplate().execute((RedisCallback<Boolean>) connection -> {
            long curTimeMillis = System.currentTimeMillis();
            byte[] lockVal = String.valueOf(curTimeMillis + finalTimeout + 1).getBytes();
            Boolean acquire = connection.setNX(finalLock.getBytes(), lockVal);
            if (Boolean.TRUE.equals(acquire)) {
                return true;
            } else {
                byte[] value = connection.get(finalLock.getBytes());
                if (Objects.nonNull(value) && value.length > 0) {
                    long expireTime = Long.parseLong(new String(value));
                    if (expireTime < curTimeMillis) {
                        return connection.set(finalLock.getBytes(), lockVal);
                    }
                }
            }
            return false;
        }));
    }

    public void unlock() {
        getRedisTemplate().delete(prefix + lockKey);
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
