package com.gdng.service.app.controller;

import com.gdng.inner.api.user.invoke.UserRemote;
import com.gdng.support.common.dto.UserDTO;
import com.gdng.support.common.dto.res.ResDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/1 17:09
 * @Description:
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/app/test")
public class TestController {

    private static final Logger log = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private UserRemote userRemote;

    @PostMapping("/register")
    public ResDTO<?> register(@RequestBody UserDTO userDTO) {
        ResDTO<?> resDTO = null;
        try {
            resDTO = userRemote.addOrUpdate(userDTO);
        } catch (Exception e) {
            log.error("新增用户异常：{}", Arrays.toString(e.getStackTrace()));
        }
        return resDTO;
    }

}
