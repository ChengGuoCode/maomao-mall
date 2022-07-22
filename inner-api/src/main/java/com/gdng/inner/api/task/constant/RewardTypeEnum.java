package com.gdng.inner.api.task.constant;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/20 11:23
 * @Description:
 * @Version: 1.0.0
 */
public enum RewardTypeEnum {
    POINT(0, "积分"),
    GOODS(1, "商品"),
    MIX_P_G(2, "积分+商品");

    private final int type;
    private final String desc;

    RewardTypeEnum(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public static RewardTypeEnum getRewardType(int type) {
        RewardTypeEnum[] enums = RewardTypeEnum.values();
        for (RewardTypeEnum anEnum : enums) {
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