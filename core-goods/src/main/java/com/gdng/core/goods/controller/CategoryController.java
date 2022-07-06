package com.gdng.core.goods.controller;

import com.gdng.core.goods.service.CategoryService;
import com.gdng.inner.api.goods.dto.CategoryResDTO;
import com.gdng.support.common.dto.res.ResDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("core/goods/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/getTab")
    public ResDTO<List<CategoryResDTO>> getTab() {
        return ResDTO.buildSuccessResult(categoryService.getTab());
    }

}
