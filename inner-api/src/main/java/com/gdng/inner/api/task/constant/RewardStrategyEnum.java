package com.gdng.inner.api.task.constant;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/20 10:40
 * @Description:
 * @Version: 1.0.0
 */
public enum RewardStrategyEnum {
    LOOP(0, "循环"),
    STEP(1, "阶梯");

    private final int strategy;
    private final String desc;

    RewardStrategyEnum(int strategy, String desc) {
        this.strategy = strategy;
        this.desc = desc;
    }

    public static RewardStrategyEnum getStrategy(int strategy) {
        RewardStrategyEnum[] enums = RewardStrategyEnum.values();
        for (RewardStrategyEnum anEnum : enums) {
            if (strategy == anEnum.strategy) {
                return anEnum;
            }
        }
        return null;
    }

    public int getStrategy() {
        return strategy;
    }

    public String getDesc() {
        return desc;
    }
}