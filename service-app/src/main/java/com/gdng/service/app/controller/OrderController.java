package com.gdng.service.app.controller;

import com.gdng.support.common.dto.res.ResDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/5 14:49
 * @Description:
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/service/app/order")
public class OrderController {

    @PostMapping("/create")
    public ResDTO<?> create() {
        return ResDTO.buildSuccessResult();
    }

    @PostMapping("/pay")
    public ResDTO<?> pay() {
        return ResDTO.buildSuccessResult();
    }

    @PostMapping("/close")
    public ResDTO<?> close() {
        return ResDTO.buildSuccessResult();
    }

    @PostMapping("/refund")
    public ResDTO<?> refund() {
        return ResDTO.buildSuccessResult();
    }

}
