package com.gdng.inner.api.order.fallback;

import com.gdng.inner.api.order.dto.OrderCreateReqDTO;
import com.gdng.inner.api.order.dto.OrderPayReqDTO;
import com.gdng.inner.api.order.dto.OrderRefundReqDTO;
import com.gdng.inner.api.order.invoke.OrderRemote;
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
public class OrderRemoteFallbackFactory implements FallbackFactory<OrderRemote> {

    @Override
    public OrderRemote create(Throwable cause) {
        return new OrderRemote() {
            @Override
            public ResDTO<?> create(List<OrderCreateReqDTO> reqDTOList) {
                return ResDTO.buildBusyResult();
            }

            @Override
            public ResDTO<?> pay(OrderPayReqDTO reqDTO) {
                return ResDTO.buildBusyResult();
            }

            @Override
            public ResDTO<?> close(OrderPayReqDTO reqDTO) {
                return ResDTO.buildBusyResult();
            }

            @Override
            public ResDTO<?> refund(OrderRefundReqDTO reqDTO) {
                return ResDTO.buildBusyResult();
            }
        };
    }
}
