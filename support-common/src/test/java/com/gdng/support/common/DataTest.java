package com.gdng.support.common;

import org.junit.Test;

import java.util.UUID;

public class DataTest {

    @Test
    public void testUUID() {
        System.out.println(UUID.randomUUID().toString().replace("-", "").toLowerCase());
    }

    @Test
    public void testCreateTimestamp() {
        System.out.println(System.currentTimeMillis());
    }

}
