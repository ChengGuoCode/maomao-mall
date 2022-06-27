package com.gdng.core.user.security;

import com.gdng.core.user.util.PrintUtil;
import com.gdng.support.common.dto.res.GlobalResponseEnum;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CoreAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        PrintUtil.writeResponse(request, response, GlobalResponseEnum.NO_PERMISSION);
    }
}
