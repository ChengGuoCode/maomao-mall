package com.gdng.core.order.constant;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/8 14:21
 * @Description:
 * @Version: 1.0.0
 */
public enum OrderSourceEnum {
    WECHAT(0, "微信小程序"),
    TASK(1, "积分任务");

    private final int sourceCode;
    private final String desc;

    OrderSourceEnum(int sourceCode, String desc) {
        this.sourceCode = sourceCode;
        this.desc = desc;
    }

    public static OrderSourceEnum getSourceByCode(int sourceCode) {
        OrderSourceEnum[] enums = OrderSourceEnum.values();
        for (OrderSourceEnum anEnum : enums) {
            if (anEnum.sourceCode == sourceCode) {
                return anEnum;
            }
        }
        return null;
    }

    public int getSourceCode() {
        return sourceCode;
    }

    public String getDesc() {
        return desc;
    }
}