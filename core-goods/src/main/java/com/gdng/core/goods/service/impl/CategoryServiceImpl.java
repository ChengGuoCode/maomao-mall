package com.gdng.core.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.gdng.core.goods.dao.service.CategoryDaoService;
import com.gdng.core.goods.service.CategoryService;
import com.gdng.entity.goods.po.CategoryPO;
import com.gdng.inner.api.goods.dto.CategoryResDTO;
import com.gdng.support.common.util.GdngBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDaoService categoryDaoService;

    @Autowired
    public CategoryServiceImpl(CategoryDaoService categoryDaoService) {
        this.categoryDaoService = categoryDaoService;
    }

    @Override
    public List<CategoryResDTO> getTab() {
        List<CategoryPO> categoryPOList = categoryDaoService.list(new QueryWrapper<CategoryPO>().eq("status", 0).eq("c_level", 1));
        List<CategoryResDTO> categoryDTOList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(categoryPOList)) {
            categoryDTOList.addAll(categoryPOList.stream().map(categoryPO -> GdngBeanUtil.copyToNewBean(categoryPO, CategoryResDTO.class)).collect(Collectors.toList()));
        }
        return categoryDTOList;
    }
}
