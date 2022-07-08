package com.gdng.support.common.cache.util;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/8 10:52
 * @Description:
 * @Version: 1.0.0
 */
public class CacheUtil {

    public static String getHashKey(Long... fields) {
        StringBuilder sb = new StringBuilder();

        for (Long field : fields) {
            sb.append(field).append("-");
        }

        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public static String getHashKey(String... fields) {
        StringBuilder sb = new StringBuilder();

        for (String field : fields) {
            sb.append(field).append("-");
        }

        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

}
