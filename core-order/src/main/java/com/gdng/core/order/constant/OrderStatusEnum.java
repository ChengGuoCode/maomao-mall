package com.gdng.core.order.constant;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/13 09:58
 * @Description:
 * @Version: 1.0.0
 */
public enum OrderStatusEnum {
    UNPAID(0, "待支付"),
    PAID(1, "支付完成"),
    CANCEL(2, "已取消"),
    REFUND_PART(3, "部分退款"),
    REFUND(4, "退款")
    ;

    private final int status;
    private final String desc;

    OrderStatusEnum(int status, String desc) {
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