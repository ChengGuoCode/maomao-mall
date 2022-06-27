package com.gdng.support.common.security;

import com.gdng.support.common.dto.res.ResDTO;

import javax.servlet.http.HttpServletRequest;

public interface ISecurityStrategy {

    ResDTO check(HttpServletRequest request);

}
