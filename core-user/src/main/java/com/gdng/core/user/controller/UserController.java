package com.gdng.core.user.controller;

import com.gdng.core.user.service.UserService;
import com.gdng.support.common.dto.UserDTO;
import com.gdng.support.common.dto.res.ResDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/core/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResDTO<UserDTO> login(@RequestBody UserDTO userDTO) {
        return ResDTO.buildSuccessResult(userService.login(userDTO));
    }

    @PostMapping("/addOrUpdate")
    public ResDTO<UserDTO> addOrUpdate(@RequestBody UserDTO userDTO) {
        return ResDTO.buildSuccessResult(userService.addOrUpdate(userDTO));
    }

}
