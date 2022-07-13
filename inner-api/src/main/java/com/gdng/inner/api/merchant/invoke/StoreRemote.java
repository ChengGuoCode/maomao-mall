package com.gdng.inner.api.merchant.invoke;

import com.gdng.inner.api.merchant.dto.StoreDTO;
import com.gdng.inner.api.merchant.fallback.StoreRemoteFallbackFactory;
import com.gdng.support.common.dto.res.ResDTO;
import com.gdng.support.common.spring.feign.FeignConf;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/13 11:23
 * @Description:
 * @Version: 1.0.0
 */
@FeignClient(value="gdng-core-merchant-procedure",path = "/core/merchant/store",fallbackFactory=
        StoreRemoteFallbackFactory.class,
        configuration = {FeignConf.class})
public interface StoreRemote {

    @PostMapping("/getStoreByIds")
    ResDTO<List<StoreDTO>> getStoreByIds(@RequestBody List<Long> storeIds);

}
