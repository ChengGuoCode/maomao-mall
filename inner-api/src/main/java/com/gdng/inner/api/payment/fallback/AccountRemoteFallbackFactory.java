package com.gdng.inner.api.payment.fallback;

import com.gdng.inner.api.payment.dto.AccountDTO;
import com.gdng.inner.api.payment.invoke.AccountRemote;
import com.gdng.support.common.dto.res.ResDTO;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/19 13:45
 * @Description:
 * @Version: 1.0.0
 */
@Component
public class AccountRemoteFallbackFactory implements FallbackFactory<AccountRemote> {
    @Override
    public AccountRemote create(Throwable cause) {
        return new AccountRemote() {
            @Override
            public ResDTO<?> addOrUpdate(AccountDTO accDTO) {
                return ResDTO.buildBusyResult();
            }
        };
    }
}
