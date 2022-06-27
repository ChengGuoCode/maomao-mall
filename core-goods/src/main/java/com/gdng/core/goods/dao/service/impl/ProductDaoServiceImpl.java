package com.gdng.core.goods.dao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdng.core.goods.dao.service.ProductDaoService;
import com.gdng.entity.goods.dao.ProductDao;
import com.gdng.entity.goods.po.ProductPO;
import org.springframework.stereotype.Service;

@Service
public class ProductDaoServiceImpl extends ServiceImpl<ProductDao, ProductPO> implements ProductDaoService {
}
