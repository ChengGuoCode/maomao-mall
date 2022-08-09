package com.gdng.core.user.controller;

import com.gdng.core.user.service.WxUserService;
import com.gdng.inner.api.user.dto.WxUserDTO;
import com.gdng.support.common.dto.UserDTO;
import com.gdng.support.common.dto.res.ResDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/8/1 18:18
 * @Description:
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/core/user/wx")
public class WxUserController {

    private final WxUserService wxUserService;

    @Autowired
    public WxUserController(WxUserService wxUserService) {
        this.wxUserService = wxUserService;
    }

    @PostMapping("/login")
    public ResDTO<UserDTO> login(@RequestBody WxUserDTO userDTO) {
        return ResDTO.buildSuccessResult(wxUserService.login(userDTO));
    }

}
