package com.gdng.inner.api.goods.fallback;

import com.gdng.inner.api.goods.dto.CarouselResDTO;
import com.gdng.inner.api.goods.dto.StoreProductReqDTO;
import com.gdng.inner.api.goods.dto.StoreProductResDTO;
import com.gdng.inner.api.goods.invoke.StoreProductRemote;
import com.gdng.support.common.dto.res.PageResDTO;
import com.gdng.support.common.dto.res.ResDTO;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/5 15:13
 * @Description:
 * @Version: 1.0.0
 */
@Component
public class StoreProductRemoteFallbackFactory implements FallbackFactory<StoreProductRemote> {
    @Override
    public StoreProductRemote create(Throwable cause) {
        return new StoreProductRemote() {
            @Override
            public ResDTO<List<CarouselResDTO>> getCarousel() {
                return ResDTO.buildBusyResult();
            }

            @Override
            public ResDTO<PageResDTO<StoreProductResDTO>> getGoodsList(StoreProductReqDTO reqDTO) {
                return ResDTO.buildBusyResult();
            }
        };
    }
}
