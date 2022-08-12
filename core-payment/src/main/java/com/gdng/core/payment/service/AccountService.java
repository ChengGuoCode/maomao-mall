package com.gdng.core.payment.service;

import com.gdng.inner.api.payment.dto.AccountDTO;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/19 11:18
 * @Description:
 * @Version: 1.0.0
 */
public interface AccountService {

    void addOrUpdate(AccountDTO accDTO);

    Long getAccBalance(String uid);

}
