package com.gdng.core.goods.service;

import com.gdng.inner.api.goods.dto.CarouselResDTO;
import com.gdng.inner.api.goods.dto.StoreProductReqDTO;
import com.gdng.inner.api.goods.dto.StoreProductResDTO;
import com.gdng.inner.api.goods.dto.StoreProductSkuStockDTO;
import com.gdng.support.common.dto.res.PageResDTO;

import java.util.List;

public interface StoreProductService {

    List<CarouselResDTO> getCarousel();

    PageResDTO<StoreProductResDTO> getGoodsList(StoreProductReqDTO reqDTO);

    void lockStock(List<StoreProductSkuStockDTO> reqDTOs);

    void releaseStock(List<StoreProductSkuStockDTO> reqDTOs);

    void reduceStock(List<StoreProductSkuStockDTO> reqDTOs);

}
