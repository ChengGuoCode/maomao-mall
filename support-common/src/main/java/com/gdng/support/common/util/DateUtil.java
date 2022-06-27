package com.gdng.support.common.util;

import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.Date;

public class DateUtil extends DateUtils {

    private static final String DATE_TIME_LONG_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final String DATE_TIME_LONG_MILLIS_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";
    private static final String DATE_TIME_LONG_MILLIS_UTC_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    private static final String DATE_TIME_SHORT_PATTERN = "yyyyMMddHHmmss";
    private static final String DATE_TIME_SHORT_MILLIS_PATTERN = "yyyyMMddHHmmssSSS";
    private static final String DATE_LONG_PATTERN = "yyyy-MM-dd";
    private static final String DATE_SHORT_PATTERN = "yyyyMMdd";
    private static final String DATE_PATTERN_1 = "yyyy/MM/dd";
    private static final String TIME_LONG_PATTERN = "HH:mm:ss";

    public static Date parseDateTimeLong(String dateStr) throws ParseException {
        return parseDate(dateStr, DATE_TIME_LONG_PATTERN);
    }

}
