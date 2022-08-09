package com.gdng.support.common;

import com.gdng.support.common.security.SecurityStrategyUtil;
import org.junit.Test;

import java.util.UUID;

public class DataTest {

    @Test
    public void testUUID() {
        System.out.println(UUID.randomUUID().toString().replace("-", "").toUpperCase());
    }

    @Test
    public void testCreateTimestamp() {
        System.out.println(System.currentTimeMillis());
    }

    @Test
    public void testBase64() {
        String uuid = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        System.out.println(uuid);
        String secret = SecurityStrategyUtil.base64Encode(uuid);
        System.out.println(secret);
        System.out.println(SecurityStrategyUtil.base64Decode(secret));
    }

}
