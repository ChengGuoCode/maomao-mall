package com.gdng.core.goods.controller;

import com.gdng.core.goods.service.StoreProductService;
import com.gdng.inner.api.goods.dto.*;
import com.gdng.support.common.dto.res.PageResDTO;
import com.gdng.support.common.dto.res.ResDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/getGoodsList")
    public ResDTO<PageResDTO<StoreProductResDTO>> getGoodsList(@RequestBody StoreProductReqDTO reqDTO) {
        return ResDTO.buildSuccessResult(storeProductService.getGoodsList(reqDTO));
    }

    @PostMapping("/lockStock")
    public ResDTO<?> lockStock(@RequestBody List<StoreProductSkuStockDTO> reqDTOs) {
        storeProductService.lockStock(reqDTOs);
        return ResDTO.buildSuccessResult();
    }

    @PostMapping("/releaseStock")
    public ResDTO<?> releaseStock(@RequestBody List<StoreProductSkuStockDTO> reqDTOs) {
        storeProductService.releaseStock(reqDTOs);
        return ResDTO.buildSuccessResult();
    }

    @PostMapping("/reduceStock")
    public ResDTO<?> reduceStock(@RequestBody List<StoreProductSkuStockDTO> reqDTOs) {
        storeProductService.reduceStock(reqDTOs);
        return ResDTO.buildSuccessResult();
    }

    @PostMapping("/getStoreProductSkuStock")
    public ResDTO<List<StoreProductSkuStockResDTO>> getStoreProductSkuStock(@RequestBody List<StoreProductSkuStockDTO> reqDTOs) {
        return ResDTO.buildSuccessResult(storeProductService.getStoreProductSkuStock(reqDTOs));
    }

}
