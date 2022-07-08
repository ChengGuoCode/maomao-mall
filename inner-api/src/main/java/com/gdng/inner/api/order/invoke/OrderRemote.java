package com.gdng.inner.api.order.invoke;

import com.gdng.inner.api.order.dto.OrderCloseReqDTO;
import com.gdng.inner.api.order.dto.OrderCreateReqDTO;
import com.gdng.inner.api.order.dto.OrderCreateResDTO;
import com.gdng.inner.api.order.dto.OrderRefundReqDTO;
import com.gdng.inner.api.order.fallback.OrderRemoteFallbackFactory;
import com.gdng.support.common.dto.res.ResDTO;
import com.gdng.support.common.spring.feign.FeignConf;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/6 16:15
 * @Description:
 * @Version: 1.0.0
 */
@FeignClient(value="gdng-core-order-procedure",path = "/core/order",fallbackFactory=
        OrderRemoteFallbackFactory.class,
        configuration = {FeignConf.class})
public interface OrderRemote {

    @PostMapping("/create")
    ResDTO<OrderCreateResDTO> create(@RequestBody OrderCreateReqDTO reqDTO);

    @PostMapping("/close")
    ResDTO<?> close(@RequestBody OrderCloseReqDTO reqDTO);

    @PostMapping("/refund")
    ResDTO<?> refund(@RequestBody OrderRefundReqDTO reqDTO);

}
