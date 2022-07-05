package com.gdng.service.app.controller;

import com.gdng.inner.api.order.dto.ShareReqDTO;
import com.gdng.support.common.dto.res.ResDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/5 14:14
 * @Description:
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/service/app/share")
public class ShareController {

    @PostMapping("/getShareList")
    public ResDTO<?> getShareList(@RequestBody ShareReqDTO reqDTO) {
        return ResDTO.buildSuccessResult();
    }

}
