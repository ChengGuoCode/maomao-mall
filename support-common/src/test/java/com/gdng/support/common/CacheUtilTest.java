package com.gdng.support.common;

import com.gdng.support.common.cache.util.CacheUtil;
import org.junit.Test;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/8 10:56
 * @Description:
 * @Version: 1.0.0
 */
public class CacheUtilTest {

    @Test
    public void testGetHashKey() {
        System.out.println(CacheUtil.getHashKey(123L, 456L, 789L));
    }

}
