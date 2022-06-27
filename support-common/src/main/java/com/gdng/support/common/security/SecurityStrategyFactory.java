package com.gdng.support.common.security;

import com.gdng.support.common.security.impl.RSASecurityStrategy;

public class SecurityStrategyFactory {

    public static ISecurityStrategy getInstance(SecurityStrategyEnum strategy) {
        switch (strategy) {
            case RSA:
                if (strategy.isAuthenticate()) {
                    return new RSASecurityStrategy();
                }
                break;
            case NONE:
                break;
        }
        throw new IllegalArgumentException("无效的安全策略配置");
    }

}
