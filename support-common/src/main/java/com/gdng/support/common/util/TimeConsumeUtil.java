package com.gdng.support.common.util;

import java.util.Arrays;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/22 18:00
 * @Description:
 * @Version: 1.0.0
 */
public class TimeConsumeUtil {

    private static final ThreadLocal<Long> START_POINT = new ThreadLocal<>();

    public static void mark() {
        START_POINT.set(System.currentTimeMillis());
    }

    public static void main(String[] args) {
        System.out.println("------------------" + Thread.currentThread().getStackTrace()[1].getClassName());
    }

}
