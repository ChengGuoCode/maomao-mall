package com.gdng.core.order.constant;

import static com.gdng.support.common.constant.StringConstant.WELL_SIGN;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/8 11:29
 * @Description:
 * @Version: 1.0.0
 */
public enum RecipientTypeEnum {
    STORE(0, "店铺收款");

    private final int type;
    private final String desc;

    RecipientTypeEnum(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public static String getStoreRecipientId(Long storeId) {
        return STORE.type + WELL_SIGN + storeId;
    }

    public int getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
}