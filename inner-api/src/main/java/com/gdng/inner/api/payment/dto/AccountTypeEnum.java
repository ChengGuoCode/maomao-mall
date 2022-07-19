package com.gdng.inner.api.payment.dto;

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

    public static AccountTypeEnum getAccByType(int type) {
        AccountTypeEnum[] enums = AccountTypeEnum.values();
        for (AccountTypeEnum anEnum : enums) {
            if (type == anEnum.type) {
                return anEnum;
            }
        }
        return null;
    }

    public int getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
}