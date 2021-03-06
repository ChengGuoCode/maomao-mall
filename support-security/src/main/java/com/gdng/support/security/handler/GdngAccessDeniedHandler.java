package com.gdng.support.security.handler;

import com.gdng.support.common.dto.res.GlobalResponseEnum;
import com.gdng.support.common.util.PrintUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GdngAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        PrintUtil.writeResponse(request, response, GlobalResponseEnum.NO_PERMISSION);
    }
}
