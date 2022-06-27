package com.gdng.support.common.security;

public enum SecurityStrategyEnum {
    RSA("RSA", true),
    NONE("", true),
    ;

    private final String name;
    private final boolean authenticate;

    SecurityStrategyEnum(String name, boolean authenticate) {
        this.name = name;
        this.authenticate = authenticate;
    }

    public String getName() {
        return name;
    }

    public boolean isAuthenticate() {
        return authenticate;
    }

    public static SecurityStrategyEnum getStrategyByName(String strategyName) {
        SecurityStrategyEnum[] enums = SecurityStrategyEnum.values();
        for (SecurityStrategyEnum anEnum : enums) {
            if (anEnum.getName().equalsIgnoreCase(strategyName)) {
                return anEnum;
            }
        }
        return NONE;
    }
}
