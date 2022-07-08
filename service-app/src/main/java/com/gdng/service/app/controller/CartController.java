package com.gdng.service.app.controller;

import com.gdng.inner.api.order.dto.CartReqDTO;
import com.gdng.inner.api.order.dto.CartResDTO;
import com.gdng.inner.api.order.invoke.CartRemote;
import com.gdng.support.common.dto.UserDTO;
import com.gdng.support.common.dto.res.ResDTO;
import com.gdng.support.common.spring.SpringContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/5 10:50
 * @Description:
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/service/app/cart")
public class CartController {

    private final CartRemote cartRemote;

    @Autowired
    public CartController(CartRemote cartRemote) {
        this.cartRemote = cartRemote;
    }

    @PostMapping("/addOrUpdateProduct")
    public ResDTO<?> addOrUpdateProduct(@RequestBody CartReqDTO reqDTO) {
        UserDTO user = SpringContextHolder.getUser();
        reqDTO.setUid(user.getId());
        return cartRemote.addOrUpdateProduct(reqDTO);
    }

    @PostMapping("/removeProduct")
    public ResDTO<?> removeProduct(@RequestBody CartReqDTO reqDTO) {
        UserDTO user = SpringContextHolder.getUser();
        reqDTO.setUid(user.getId());
        return cartRemote.removeProduct(reqDTO);
    }

    @GetMapping("/getCartList")
    public ResDTO<List<CartResDTO>> getCartList() {
        UserDTO user = SpringContextHolder.getUser();
        return cartRemote.getCartList(user.getId());
    }

}
