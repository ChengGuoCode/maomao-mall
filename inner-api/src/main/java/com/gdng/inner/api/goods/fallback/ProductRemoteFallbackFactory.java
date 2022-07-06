package com.gdng.inner.api.goods.fallback;

import com.gdng.inner.api.goods.invoke.ProductRemote;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/5 15:09
 * @Description:
 * @Version: 1.0.0
 */
@Component
public class ProductRemoteFallbackFactory implements FallbackFactory<ProductRemote> {
    @Override
    public ProductRemote create(Throwable cause) {
        return new ProductRemote() {
        };
    }
}
