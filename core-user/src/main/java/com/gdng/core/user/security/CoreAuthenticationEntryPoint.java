package com.gdng.core.user.security;

import com.gdng.core.user.util.PrintUtil;
import com.gdng.support.common.dto.res.GlobalResponseEnum;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CoreAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        PrintUtil.writeResponse(request, response, GlobalResponseEnum.ILLEGAL_ACCESS);
    }
}
