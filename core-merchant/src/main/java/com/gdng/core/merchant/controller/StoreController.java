package com.gdng.core.merchant.controller;

import com.gdng.core.merchant.service.StoreService;
import com.gdng.inner.api.merchant.dto.StoreDTO;
import com.gdng.support.common.dto.res.ResDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@RestController
@RequestMapping("core/merchant/store")
public class StoreController {

    private final StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @PostMapping("/getStoreByIds")
    public ResDTO<List<StoreDTO>> getStoreByIds(@RequestBody @Valid @NotEmpty(message = "店铺ID不能为空") List<Long> storeIds) {
        return ResDTO.buildSuccessResult(storeService.getStoreByIds(storeIds));
    }
}
