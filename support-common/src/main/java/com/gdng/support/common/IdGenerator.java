package com.gdng.support.common;

import com.gdng.support.common.dto.res.GlobalResponseEnum;
import com.gdng.support.common.exception.GdngException;

import java.io.Serializable;
import java.util.*;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/2/16 14:40
 * @Description: 抵用券编码生成器
 * @Version: 1.0.0
 */
public class IdGenerator {

    private static final char[] WORD_DICTIONARY = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q'
            ,'R','S','T','U','V','W','X','Y','Z'};

    private static final Map<Long, Serializable> DIGIT_MAP = new HashMap<>();

    static {
        long digit = 10L;
        for (long i = 0; i < digit; i++) {
            DIGIT_MAP.put(i, i);
        }
        for (char c : WORD_DICTIONARY) {
            DIGIT_MAP.put(digit++, c);
        }

        Calendar instance = Calendar.getInstance();
        instance.set(2022, Calendar.JULY, 7, 0, 0, 0);
        START_INDEX = instance.getTimeInMillis();
    }

    private static final int MILLIS_CYCLE = 1000;
    private static final int SEC_CYCLE = 60;
    private static final int MIN_CYCLE = 60;
    private static final int HOUR_CYCLE = 24;
    private static final int DAY_CYCLE = 30;
    private static final int MON_CYCLE = 12;

    private static final long MILLIS = 1L;
    private static final long SEC = MILLIS_CYCLE * MILLIS;
    private static final long MIN = SEC_CYCLE * SEC;
    private static final long HOUR = MIN_CYCLE * MIN;
    private static final long DAY = HOUR_CYCLE * HOUR;
    private static final long MON = DAY_CYCLE * DAY;
    private static final long YEAR = MON_CYCLE * MON;

    private static final long START_INDEX;

    private long lastTimestamp = -1L;

    private int sequence = 0;

    private String codePrefix = "";

    public IdGenerator() {
    }

    public IdGenerator(String codePrefix) {
        this.codePrefix = codePrefix;
    }

    public synchronized String nextId() {
        String id;
        long growth = System.currentTimeMillis() - START_INDEX;
        if (growth > lastTimestamp) {
            sequence = 0;
            id = transferTimestamp(growth, sequence);
        } else if (growth == lastTimestamp) {
            if (sequence > 8) {
                growth = getNextMillis(growth, lastTimestamp);
                sequence = 0;
            }
            id = transferTimestamp(growth, ++sequence);
        } else {
            throw new GdngException(GlobalResponseEnum.PARAM_ERR, "系统时钟异常");
        }
        lastTimestamp = growth;
        return id;
    }

    public List<String> batchIds(int num) {
        List<String> codeList = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            codeList.add(nextId());
        }
        return codeList;
    }

    private long getNextMillis(long growth, long lastTimestamp) {
        while (growth <= lastTimestamp) {
            growth = System.currentTimeMillis() - START_INDEX;
        }
        return growth;
    }

    private String transferTimestamp(long growth, int sequence) {
        long year = growth / YEAR;
        long mon = (growth / MON) % MON_CYCLE;
        long day = (growth / DAY) % DAY_CYCLE;
        long hour = (growth / HOUR) % HOUR_CYCLE;
        long min = (growth / MIN) % MIN_CYCLE;
        long sec = (growth / SEC) % SEC_CYCLE;
        long millis = (growth / MILLIS) % MILLIS_CYCLE;
        return codePrefix +
                transferTimeStr(mon, TimestampUnitEnum.MON) +
                transferTimeStr(min, TimestampUnitEnum.MIN) +
                transferTimeStr(day, TimestampUnitEnum.DAY) +
                transferTimeStr(millis, TimestampUnitEnum.MILLIS) +
                transferTimeStr(hour, TimestampUnitEnum.HOUR) +
                transferTimeStr(sec, TimestampUnitEnum.SEC) +
                transferTimeStr(year, TimestampUnitEnum.YEAR) +
                sequence;
    }

    private String transferTimeStr(long val, TimestampUnitEnum unitEnum) {
        String unitVal;
        switch (unitEnum) {
            case YEAR:
            case MON:
            case DAY:
            case HOUR:
                unitVal = DIGIT_MAP.get(val).toString();
                break;
            case MIN:
            case SEC:
                unitVal = val < 10 ? "0" + val : val + "";
                break;
            case MILLIS:
                unitVal = val < 10 ? "00" + val : (val < 100 ? "0" + val : "" + val);
                break;
            default:
                throw new GdngException(GlobalResponseEnum.PARAM_ERR, "无效的时间单位类型");
        }
        return unitVal;
    }

    private enum TimestampUnitEnum {
        YEAR,
        MON,
        DAY,
        HOUR,
        MIN,
        SEC,
        MILLIS
    }

}
