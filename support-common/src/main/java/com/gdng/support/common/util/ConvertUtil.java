package com.gdng.support.common.util;

public class ConvertUtil {

    public static Long parseObjToLong(Object obj) {
        if (obj == null || "".equals(obj)) {
            return null;
        }
        return Long.valueOf(obj.toString());
    }

    public static Integer parseObjToInt(Object obj) {
        if (obj == null || "".equals(obj)) {
            return null;
        }
        return Integer.valueOf(obj.toString());
    }

}
