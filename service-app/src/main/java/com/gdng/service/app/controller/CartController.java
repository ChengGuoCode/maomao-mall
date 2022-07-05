package com.gdng.service.app.controller;

import com.gdng.inner.api.order.dto.CartReqDTO;
import com.gdng.support.common.dto.UserDTO;
import com.gdng.support.common.dto.res.GlobalResponseEnum;
import com.gdng.support.common.dto.res.ResDTO;
import com.gdng.support.common.exception.GdngException;
import com.gdng.support.common.spring.SpringContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/5 10:50
 * @Description:
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/service/app/cart")
public class CartController {

    @PostMapping("/addProduct")
    public ResDTO<?> addProduct(@RequestBody CartReqDTO reqDTO) {
        UserDTO user = SpringContextHolder.getUser();
        if (user == null) {
            throw new GdngException(GlobalResponseEnum.NO_LOGIN);
        }
        reqDTO.setUid(user.getId());
        return ResDTO.buildSuccessResult();
    }

    @PostMapping("/reduceProduct")
    public ResDTO<?> reduceProduct(@RequestBody CartReqDTO reqDTO) {
        UserDTO user = SpringContextHolder.getUser();
        if (user == null) {
            throw new GdngException(GlobalResponseEnum.NO_LOGIN);
        }
        reqDTO.setUid(user.getId());
        return ResDTO.buildSuccessResult();
    }

    @GetMapping("/getCartList")
    public ResDTO<?> getCartList() {
        UserDTO user = SpringContextHolder.getUser();
        if (user == null) {
            throw new GdngException(GlobalResponseEnum.NO_LOGIN);
        }
        return ResDTO.buildSuccessResult();
    }

}
