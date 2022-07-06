package com.gdng.inner.api.goods.fallback;

import com.gdng.inner.api.goods.dto.GoodsReqDTO;
import com.gdng.inner.api.goods.invoke.StoreProductRemote;
import com.gdng.support.common.dto.res.PageResDTO;
import com.gdng.support.common.dto.res.ResDTO;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

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
            public ResDTO<?> getCarousel() {
                return ResDTO.buildBusyResult();
            }

            @Override
            public ResDTO<PageResDTO<?>> getGoodsList(GoodsReqDTO reqDTO) {
                return ResDTO.buildBusyResult();
            }
        };
    }
}
