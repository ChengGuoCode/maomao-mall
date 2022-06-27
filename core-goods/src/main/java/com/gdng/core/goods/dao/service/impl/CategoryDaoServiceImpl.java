package com.gdng.core.goods.dao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdng.core.goods.dao.service.CategoryDaoService;
import com.gdng.entity.goods.dao.CategoryDao;
import com.gdng.entity.goods.po.CategoryPO;
import org.springframework.stereotype.Service;

@Service
public class CategoryDaoServiceImpl extends ServiceImpl<CategoryDao, CategoryPO> implements CategoryDaoService {
}
