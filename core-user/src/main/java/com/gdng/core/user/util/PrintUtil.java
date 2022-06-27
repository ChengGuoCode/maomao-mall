package com.gdng.core.user.util;

import com.gdng.support.common.dto.res.GlobalResponseEnum;
import com.gdng.support.common.dto.res.ResDTO;
import com.gdng.support.common.exception.GdngException;
import com.gdng.support.common.util.JacksonUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class PrintUtil {

    public static void writeResponse(HttpServletRequest request, HttpServletResponse response, GlobalResponseEnum responseEnum) throws IOException {
        Object err = request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        ResDTO resDTO = ResDTO.buildFailResult(responseEnum);
        if (err instanceof GdngException) {
            GdngException exception = (GdngException) err;
            resDTO.setCode(exception.getCode());
            resDTO.setMsg(exception.getMessage());
        }
        String resBody = JacksonUtil.anyToJson(resDTO);
        response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        PrintWriter printWriter = response.getWriter();
        printWriter.print(resBody);
        printWriter.flush();
        printWriter.close();
    }

}
