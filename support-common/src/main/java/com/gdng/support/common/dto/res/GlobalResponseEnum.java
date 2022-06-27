package com.gdng.support.common.dto.res;

public enum GlobalResponseEnum {
    SUCCESS(0, "请求成功"),

    NO_LOGIN(1001, "用户未登录"),
    USER_NONEXIST(1002, "用户不存在"),
    PASSWORD_ERR(1003, "密码错误"),
    TOKEN_INVALID(1004, "无效Token"),
    TOKEN_EXP(1005, "Token已过期"),
    ILLEGAL_ACCESS(1006, "认证失败"),
    NO_PERMISSION(1007, "无访问权限"),
    SESSION_KEY_LACK(1008, "缺少sessionKey"),
    SIGN_ERR(1009, "签名错误"),
    REQUEST_EXP(1010, "请求已失效"),

    JSON_CONVERT(2001, "json转换异常[%s]"),

    BIZ_PARAM_ERR(3001, "%s"),
    PARAM_ERR(3002, "参数异常[%s]"),

    SYS_UPGRADE(8001, "应用版本太低，请升级系统"),

    SYSTEM_ERR(9001, "系统不舒服，请稍后重试"),
    SYSTEM_BUSY(9002, "系统繁忙"),
    ;

    private final int code;
    private final String msg;

    GlobalResponseEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
