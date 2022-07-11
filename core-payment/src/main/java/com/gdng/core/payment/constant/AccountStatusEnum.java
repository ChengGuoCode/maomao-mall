package com.gdng.core.payment.constant;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/11 14:45
 * @Description:
 * @Version: 1.0.0
 */
public enum AccountStatusEnum {
    NORMAL(0, "正常"),
    PAYABLE(1, "可支付"),
    WITHDRAWABLE(2, "可提现"),
    FROZEN(3, "冻结")
    ;
    //账户状态 0-正常，1-可支付不可提现，2-可提现不可支付，3-冻结
    private final int status;
    private final String desc;

    AccountStatusEnum(int status, String desc) {
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