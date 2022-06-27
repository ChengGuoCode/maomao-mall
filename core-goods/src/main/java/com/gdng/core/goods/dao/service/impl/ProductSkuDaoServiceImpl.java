package com.gdng.core.goods.dao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdng.core.goods.dao.service.ProductSkuDaoService;
import com.gdng.entity.goods.dao.ProductSkuDao;
import com.gdng.entity.goods.po.ProductSkuPO;
import org.springframework.stereotype.Service;

@Service
public class ProductSkuDaoServiceImpl extends ServiceImpl<ProductSkuDao, ProductSkuPO> implements ProductSkuDaoService {
}
