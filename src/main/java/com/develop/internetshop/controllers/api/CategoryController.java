package com.develop.internetshop.controllers.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.develop.internetshop.entities.Category.Category;
import com.develop.internetshop.entities.Category.CategoryRepository;

/**
* CategoryController
*/
@RestController
@RequestMapping(path = "api/v1/categories")
public class CategoryController extends BaseApiController<Category, Long> {
    public CategoryController(CategoryRepository categoryRepository) {
        super(categoryRepository);
    }
}