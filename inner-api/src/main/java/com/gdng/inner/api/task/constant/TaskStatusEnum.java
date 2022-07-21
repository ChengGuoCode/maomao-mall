package com.gdng.inner.api.task.constant;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/21 09:52
 * @Description:
 * @Version: 1.0.0
 */
public enum TaskStatusEnum {
    NORMAL(0, "正常"),
    INVALID(1, "失效")
    ;

    private final int status;
    private final String desc;

    TaskStatusEnum(int status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public int getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }
}