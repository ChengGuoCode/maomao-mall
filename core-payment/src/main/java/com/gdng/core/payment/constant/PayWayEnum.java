package com.gdng.core.payment.constant;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/11 14:20
 * @Description:
 * @Version: 1.0.0
 */
public enum PayWayEnum {

    BALANCE(0, "余额");

    private final int code;
    private final String desc;

    PayWayEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static PayWayEnum getPayWayByCode(int payWayCode) {
        PayWayEnum[] enums = PayWayEnum.values();
        for (PayWayEnum anEnum : enums) {
            if (anEnum.code == payWayCode) {
                return anEnum;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}