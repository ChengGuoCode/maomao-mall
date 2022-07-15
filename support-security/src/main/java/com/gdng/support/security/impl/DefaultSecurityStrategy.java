package com.gdng.support.security.impl;

import com.gdng.support.common.dto.res.ResDTO;
import com.gdng.support.common.security.ISecurityStrategy;

import javax.servlet.http.HttpServletRequest;

public class DefaultSecurityStrategy implements ISecurityStrategy {
    @Override
    public ResDTO check(HttpServletRequest request) {
        return ResDTO.buildSuccessResult();
    }
}
