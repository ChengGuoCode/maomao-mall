package com.gdng.core.order.constant;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/8 14:54
 * @Description:
 * @Version: 1.0.0
 */
public enum PayWayEnum {
    BALANCE(0, "余额");

    private final int payWayCode;
    private final String desc;

    PayWayEnum(int payWayCode, String desc) {
        this.payWayCode = payWayCode;
        this.desc = desc;
    }

    public static PayWayEnum getPayWayByCode(int payWayCode) {
        PayWayEnum[] enums = PayWayEnum.values();
        for (PayWayEnum anEnum : enums) {
            if (anEnum.payWayCode == payWayCode) {
                return anEnum;
            }
        }
        return null;
    }

    public int getPayWayCode() {
        return payWayCode;
    }

    public String getDesc() {
        return desc;
    }
}