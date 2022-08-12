package com.gdng.core.payment.controller;

import com.gdng.core.payment.service.AccountService;
import com.gdng.inner.api.payment.dto.AccountDTO;
import com.gdng.support.common.dto.res.ResDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/19 11:17
 * @Description:
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("core/acc")
public class AccountController {

    private final AccountService accService;

    @Autowired
    public AccountController(AccountService accService) {
        this.accService = accService;
    }

    @PostMapping("/addOrUpdate")
    public ResDTO<?> addOrUpdate(@RequestBody AccountDTO accDTO) {
        accService.addOrUpdate(accDTO);
        return ResDTO.buildSuccessResult();
    }

    @GetMapping("/getAccBalance")
    ResDTO<Long> getAccBalance(@RequestParam String uid) {
        return ResDTO.buildSuccessResult(accService.getAccBalance(uid));
    }

}
