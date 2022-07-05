package com.gdng.service.app.controller;

import com.gdng.inner.api.goods.dto.GoodsReqDTO;
import com.gdng.support.common.dto.res.PageResDTO;
import com.gdng.support.common.dto.res.ResDTO;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/5 10:13
 * @Description:
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/service/app/home")
public class HomeController {

    @GetMapping("/getCarousel")
    public ResDTO<?> getCarousel() {
        return ResDTO.buildSuccessResult();
    }

    @GetMapping("/getTab")
    public ResDTO<?> getTab() {
        return ResDTO.buildSuccessResult();
    }

    @PostMapping("/getGoodsList")
    public ResDTO<PageResDTO<?>> getGoodsList(@RequestBody GoodsReqDTO reqDTO) {
        return ResDTO.buildSuccessResult();
    }

}
