package com.gdng.inner.api.task.constant;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/20 14:10
 * @Description:
 * @Version: 1.0.0
 */
public enum TaskTypeEnum {
    DAILY(0, "每日任务"),
    COMMON(1, "常规任务"),
    HISTORY(2, "历史任务");

    private final int type;
    private final String desc;

    TaskTypeEnum(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public static TaskTypeEnum getTaskType(int type) {
        TaskTypeEnum[] enums = TaskTypeEnum.values();
        for (TaskTypeEnum anEnum : enums) {
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