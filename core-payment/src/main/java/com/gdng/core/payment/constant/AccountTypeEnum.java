package com.gdng.core.payment.constant;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/11 14:41
 * @Description:
 * @Version: 1.0.0
 */
public enum AccountTypeEnum {
    INDIVIDUAL(0, "个人"),
    PLATFORM(1, "平台"),
    BUSINESS(2, "商家"),
    STORE(3, "店铺")
    ;

    private final int type;
    private final String desc;

    AccountTypeEnum(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public int getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
}