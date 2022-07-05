package com.gdng.service.app.controller;

import com.gdng.support.common.dto.res.ResDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/5 10:47
 * @Description:
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/service/app/goods")
public class GoodsController {

    @GetMapping("/getProductDetailByCode")
    public ResDTO<?> getProductDetailByCode(@RequestParam String productCode) {
        return ResDTO.buildSuccessResult();
    }

}
