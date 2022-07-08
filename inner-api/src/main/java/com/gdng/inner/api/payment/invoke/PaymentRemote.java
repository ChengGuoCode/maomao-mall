package com.gdng.inner.api.payment.invoke;

import com.gdng.inner.api.payment.fallback.PaymentRemoteFallbackFactory;
import com.gdng.support.common.spring.feign.FeignConf;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/8 16:31
 * @Description:
 * @Version: 1.0.0
 */
@FeignClient(value="gdng-core-payment-procedure",path = "/core/payment",fallbackFactory=
        PaymentRemoteFallbackFactory.class,
        configuration = {FeignConf.class})
public interface PaymentRemote {
}
