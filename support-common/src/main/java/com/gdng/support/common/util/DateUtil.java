package com.gdng.support.common.util;

import com.gdng.support.common.dto.res.GlobalResponseEnum;
import com.gdng.support.common.exception.GdngException;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.Calendar;
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
    private static final String START_TIME = "00:00:00";
    private static final String END_TIME = "23:59:59";

    public static boolean isSameDay(Date d1, Date d2) {
        String d1Str = DateFormatUtils.format(d1, DATE_LONG_PATTERN);
        String d2Str = DateFormatUtils.format(d2, DATE_LONG_PATTERN);
        return d1Str.equals(d2Str);
    }

    public static Date parseDateTimeLong(String dateStr) {
        try {
            return parseDate(dateStr, DATE_TIME_LONG_PATTERN);
        } catch (ParseException e) {
            throw new GdngException(GlobalResponseEnum.PARAM_ERR, "com.gdng.support.common.util.DateUtil" +
                    ".parseDateTimeLong[" + dateStr + "]");
        }
    }

    public static Date parseDateFromTime(String time) {
        Calendar instance = Calendar.getInstance();
        return parseDateTimeLong(DateFormatUtils.format(instance.getTime(), DATE_LONG_PATTERN) + " " + time);
    }

    public static Date getCurDateStart() {
        return parseDateFromTime(START_TIME);
    }

    public static Date getCurDateEnd() {
        return parseDateFromTime(END_TIME);
    }

    public static String getCurTimeStr() {
        Calendar instance = Calendar.getInstance();
        return DateFormatUtils.format(instance.getTime(), TIME_LONG_PATTERN);
    }

}
