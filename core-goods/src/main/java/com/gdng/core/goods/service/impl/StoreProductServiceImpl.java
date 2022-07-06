package com.gdng.core.goods.service.impl;

import com.gdng.core.goods.dao.service.StoreProductDaoService;
import com.gdng.core.goods.dao.service.StoreProductSkuDaoService;
import com.gdng.core.goods.service.StoreProductService;
import com.gdng.inner.api.goods.dto.CarouselResDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreProductServiceImpl implements StoreProductService {

    private final StoreProductDaoService storeProductDaoService;
    private final StoreProductSkuDaoService storeProductSkuDaoService;

    @Autowired
    public StoreProductServiceImpl(StoreProductDaoService storeProductDaoService, StoreProductSkuDaoService storeProductSkuDaoService) {
        this.storeProductDaoService = storeProductDaoService;
        this.storeProductSkuDaoService = storeProductSkuDaoService;
    }

    @Override
    public List<CarouselResDTO> getCarousel() {
        return null;
    }
}
