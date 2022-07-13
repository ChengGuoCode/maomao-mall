package com.gdng.inner.api.order.invoke;

import com.gdng.inner.api.order.dto.CartReqDTO;
import com.gdng.inner.api.order.dto.CartResDTO;
import com.gdng.inner.api.order.fallback.CartRemoteFallbackFactory;
import com.gdng.support.common.dto.res.ResDTO;
import com.gdng.support.common.spring.feign.FeignConf;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/6 16:15
 * @Description:
 * @Version: 1.0.0
 */
@FeignClient(value="gdng-core-order-procedure",path = "/core/order/cart",fallbackFactory=
        CartRemoteFallbackFactory.class,
        configuration = {FeignConf.class})
public interface CartRemote {

    @PostMapping("/addOrUpdateProduct")
    ResDTO<?> addOrUpdateProduct(@RequestBody CartReqDTO reqDTO);

    @PostMapping("/removeProduct")
    ResDTO<?> removeProduct(@RequestBody CartReqDTO reqDTO);

    @PostMapping("/getCartList")
    ResDTO<List<CartResDTO>> getCartList(@RequestBody String uid);

}
