package com.gdng.inner.api.goods.invoke;

import com.gdng.inner.api.goods.fallback.ProductRemoteFallbackFactory;
import com.gdng.support.common.spring.feign.FeignConf;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/5 15:08
 * @Description:
 * @Version: 1.0.0
 */
@FeignClient(value="gdng-core-goods-procedure",path = "/core/goods/product",fallbackFactory=
        ProductRemoteFallbackFactory.class,
        configuration = {FeignConf.class})
public interface ProductRemote {
}
