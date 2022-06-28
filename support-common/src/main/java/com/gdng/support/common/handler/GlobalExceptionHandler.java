package com.gdng.support.common.handler;

import com.gdng.support.common.dto.res.GlobalResponseEnum;
import com.gdng.support.common.dto.res.ResDTO;
import com.gdng.support.common.exception.GdngException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;

@ResponseBody
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResDTO exceptionHandler(Exception e) {
        ResDTO<Object> resDTO = new ResDTO<>();
        if (e instanceof GdngException) {
            GdngException ge = (GdngException) e;
            resDTO.setCode(ge.getCode());
            resDTO.setMsg(ge.getMessage());
        } else {
            log.error("系统异常:{}", Arrays.toString(e.getStackTrace()));
            GlobalResponseEnum systemErr = GlobalResponseEnum.SYSTEM_ERR;
            resDTO.setCode(systemErr.getCode());
            resDTO.setMsg(systemErr.getMsg());
        }
        return resDTO;
    }

}
