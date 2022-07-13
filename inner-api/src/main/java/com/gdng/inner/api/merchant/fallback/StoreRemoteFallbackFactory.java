package com.gdng.inner.api.merchant.fallback;

import com.gdng.inner.api.merchant.dto.StoreDTO;
import com.gdng.inner.api.merchant.invoke.StoreRemote;
import com.gdng.support.common.dto.res.ResDTO;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/6 16:16
 * @Description:
 * @Version: 1.0.0
 */
@Component
public class StoreRemoteFallbackFactory implements FallbackFactory<StoreRemote> {

    @Override
    public StoreRemote create(Throwable cause) {
        return new StoreRemote() {

            @Override
            public ResDTO<List<StoreDTO>> getStoreByIds(List<Long> storeIds) {
                return ResDTO.buildBusyResult();
            }
        };
    }
}
