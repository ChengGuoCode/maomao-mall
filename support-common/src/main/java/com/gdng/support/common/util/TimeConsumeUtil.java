package com.gdng.support.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/22 18:00
 * @Description:
 * @Version: 1.0.0
 */
public class TimeConsumeUtil {

    private static final Logger log = LoggerFactory.getLogger(TimeConsumeUtil.class);

    private static final ThreadLocal<Map<String, String>> START_POINT = new ThreadLocal<>();

    private static final int MAX_TRACE = 2000;

    static {
        START_POINT.set(new ConcurrentHashMap<>());
    }

    public static void markStart() {
        Map<String, String> traceMap = START_POINT.get();
        if (traceMap.size() > MAX_TRACE) {
            log.error("耗时统计日志标记开始位置异常，已超容量上限");
            return;
        }
        String[] invokePath = getInvokePath();
        traceMap.put(invokePath[0], System.currentTimeMillis() + invokePath[1]);
    }

    public static void markEnd() {
        markEnd("");
    }

    public static void markEnd(String msg) {
        Map<String, String> traceMap = START_POINT.get();
        String[] invokePath = getInvokePath();
        String startVal = traceMap.get(invokePath[0]);
        if (startVal == null) {
            log.error("耗时统计日志异常，未标记开始位置");
            return;
        }
        String[] split = startVal.split("#");
        long cost = System.currentTimeMillis() - Long.parseLong(split[0]);
        traceMap.remove(invokePath[0]);
        log.info("[" + msg + "]" + "cost：" + cost + "ms " + invokePath[0] + " #" + split[1] + "-" + invokePath[1]);
    }

    private static String[] getInvokePath() {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
        return new String[]{stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName(),
                "#" + stackTraceElement.getLineNumber()};
    }

}
