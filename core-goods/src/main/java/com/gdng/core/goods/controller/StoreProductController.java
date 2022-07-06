package com.gdng.core.goods.controller;

import com.gdng.core.goods.service.StoreProductService;
import com.gdng.inner.api.goods.dto.CarouselResDTO;
import com.gdng.support.common.dto.res.ResDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("core/goods/storeProduct")
public class StoreProductController {

    private final StoreProductService storeProductService;

    @Autowired
    public StoreProductController(StoreProductService storeProductService) {
        this.storeProductService = storeProductService;
    }

    @GetMapping("/getCarousel")
    public ResDTO<List<CarouselResDTO>> getCarousel() {
        return ResDTO.buildSuccessResult(storeProductService.getCarousel());
    }
}
