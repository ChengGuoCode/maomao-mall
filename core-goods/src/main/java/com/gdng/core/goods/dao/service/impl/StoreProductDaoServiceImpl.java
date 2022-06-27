package com.gdng.core.goods.dao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdng.core.goods.dao.service.StoreProductDaoService;
import com.gdng.entity.goods.dao.StoreProductDao;
import com.gdng.entity.goods.po.StoreProductPO;
import org.springframework.stereotype.Service;

@Service
public class StoreProductDaoServiceImpl extends ServiceImpl<StoreProductDao, StoreProductPO> implements StoreProductDaoService {
}
