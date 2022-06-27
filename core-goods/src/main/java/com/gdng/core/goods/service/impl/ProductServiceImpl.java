package com.gdng.core.goods.service.impl;

import com.gdng.core.goods.dao.service.ProductDaoService;
import com.gdng.core.goods.dao.service.ProductSkuDaoService;
import com.gdng.core.goods.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDaoService productDaoService;
    private final ProductSkuDaoService productSkuDaoService;

    @Autowired
    public ProductServiceImpl(ProductDaoService productDaoService, ProductSkuDaoService productSkuDaoService) {
        this.productDaoService = productDaoService;
        this.productSkuDaoService = productSkuDaoService;
    }
}
