package com.gdng.inner.api.order.fallback;

import com.gdng.inner.api.order.dto.CartReqDTO;
import com.gdng.inner.api.order.dto.CartResDTO;
import com.gdng.inner.api.order.invoke.CartRemote;
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
public class CartRemoteFallbackFactory implements FallbackFactory<CartRemote> {
    @Override
    public CartRemote create(Throwable cause) {
        return new CartRemote() {
            @Override
            public ResDTO<?> addOrUpdateProduct(CartReqDTO reqDTO) {
                return ResDTO.buildBusyResult();
            }

            @Override
            public ResDTO<?> removeProduct(CartReqDTO reqDTO) {
                return ResDTO.buildBusyResult();
            }

            @Override
            public ResDTO<List<CartResDTO>> getCartList(String uid) {
                return ResDTO.buildBusyResult();
            }
        };
    }
}
