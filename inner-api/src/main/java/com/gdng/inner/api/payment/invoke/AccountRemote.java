package com.gdng.inner.api.payment.invoke;

import com.gdng.inner.api.payment.dto.AccountDTO;
import com.gdng.inner.api.payment.fallback.AccountRemoteFallbackFactory;
import com.gdng.support.common.dto.res.ResDTO;
import com.gdng.support.common.spring.feign.FeignConf;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/19 13:44
 * @Description:
 * @Version: 1.0.0
 */
@FeignClient(value="gdng-core-payment-procedure",path = "/core/acc",fallbackFactory=
        AccountRemoteFallbackFactory.class,
        configuration = {FeignConf.class})
public interface AccountRemote {

    @PostMapping("/addOrUpdate")
    ResDTO<?> addOrUpdate(@RequestBody AccountDTO accDTO);

    @GetMapping("/getAccBalance")
    ResDTO<Long> getAccBalance(@RequestParam String uid);

}
