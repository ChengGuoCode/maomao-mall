package com.gdng.core.order.service;

import com.gdng.inner.api.order.dto.CartReqDTO;
import com.gdng.inner.api.order.dto.CartResDTO;

import java.util.List;

public interface CartService {

    void addOrUpdateProduct(CartReqDTO reqDTO);

    void removeProduct(CartReqDTO reqDTO);

    List<CartResDTO> getCartList(String uid);

}
