package com.gdng.core.goods.service.impl;

import com.gdng.core.goods.dao.service.CategoryDaoService;
import com.gdng.core.goods.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDaoService categoryDaoService;

    @Autowired
    public CategoryServiceImpl(CategoryDaoService categoryDaoService) {
        this.categoryDaoService = categoryDaoService;
    }
}
