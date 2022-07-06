package com.gdng.inner.api.goods.invoke;

import com.gdng.inner.api.goods.dto.CarouselResDTO;
import com.gdng.inner.api.goods.dto.StoreProductReqDTO;
import com.gdng.inner.api.goods.dto.StoreProductResDTO;
import com.gdng.inner.api.goods.fallback.StoreProductRemoteFallbackFactory;
import com.gdng.support.common.dto.res.PageResDTO;
import com.gdng.support.common.dto.res.ResDTO;
import com.gdng.support.common.spring.feign.FeignConf;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/5 15:11
 * @Description:
 * @Version: 1.0.0
 */
@FeignClient(value="gdng-core-goods-procedure",path = "/core/goods/storeProduct",fallbackFactory=
        StoreProductRemoteFallbackFactory.class,
        configuration = {FeignConf.class})
public interface StoreProductRemote {

    @GetMapping("/getCarousel")
    ResDTO<List<CarouselResDTO>> getCarousel();

    @PostMapping("/getGoodsList")
    ResDTO<PageResDTO<StoreProductResDTO>> getGoodsList(@RequestBody StoreProductReqDTO reqDTO);

}
