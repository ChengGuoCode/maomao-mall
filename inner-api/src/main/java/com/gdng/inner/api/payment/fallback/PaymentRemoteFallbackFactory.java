package com.gdng.inner.api.payment.fallback;

import com.gdng.inner.api.payment.invoke.PaymentRemote;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/6 16:16
 * @Description:
 * @Version: 1.0.0
 */
@Component
public class PaymentRemoteFallbackFactory implements FallbackFactory<PaymentRemote> {

    @Override
    public PaymentRemote create(Throwable cause) {
        return new PaymentRemote() {
        };
    }
}
