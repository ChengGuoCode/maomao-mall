package com.gdng.service.app.controller;

import com.gdng.inner.api.goods.dto.CarouselResDTO;
import com.gdng.inner.api.goods.dto.CategoryResDTO;
import com.gdng.inner.api.goods.dto.StoreProductReqDTO;
import com.gdng.inner.api.goods.dto.StoreProductResDTO;
import com.gdng.inner.api.goods.invoke.CategoryRemote;
import com.gdng.inner.api.goods.invoke.StoreProductRemote;
import com.gdng.support.common.dto.res.PageResDTO;
import com.gdng.support.common.dto.res.ResDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/5 10:13
 * @Description:
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/service/app/home")
public class HomeController {

    private final StoreProductRemote storeProductRemote;
    private final CategoryRemote categoryRemote;

    @Autowired
    public HomeController(StoreProductRemote storeProductRemote, CategoryRemote categoryRemote) {
        this.storeProductRemote = storeProductRemote;
        this.categoryRemote = categoryRemote;
    }

    @GetMapping("/getCarousel")
    public ResDTO<List<CarouselResDTO>> getCarousel() {
        return storeProductRemote.getCarousel();
    }

    @GetMapping("/getTab")
    public ResDTO<List<CategoryResDTO>> getTab() {
        return categoryRemote.getTab();
    }

    @PostMapping("/getGoodsList")
    public ResDTO<PageResDTO<StoreProductResDTO>> getGoodsList(@RequestBody StoreProductReqDTO reqDTO) {
        return storeProductRemote.getGoodsList(reqDTO);
    }

}
