package com.gdng.core.order.controller;

import com.gdng.core.order.service.CartService;
import com.gdng.inner.api.order.dto.CartReqDTO;
import com.gdng.inner.api.order.dto.CartResDTO;
import com.gdng.support.common.dto.res.ResDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("core/order/cart")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/addOrUpdateProduct")
    public ResDTO<?> addOrUpdateProduct(@RequestBody @Valid CartReqDTO reqDTO) {
        cartService.addOrUpdateProduct(reqDTO);
        return ResDTO.buildSuccessResult();
    }

    @PostMapping("/removeProduct")
    public ResDTO<?> removeProduct(@RequestBody CartReqDTO reqDTO) {
        cartService.removeProduct(reqDTO);
        return ResDTO.buildSuccessResult();
    }

    @PostMapping("/getCartList")
    public ResDTO<List<CartResDTO>> getCartList(@RequestBody String uid) {
        return ResDTO.buildSuccessResult(cartService.getCartList(uid));
    }

}
