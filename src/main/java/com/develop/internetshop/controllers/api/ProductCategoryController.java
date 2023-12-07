package com.develop.internetshop.controllers.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.develop.internetshop.entities.Product.ProductCategory.ProductCategory;
import com.develop.internetshop.entities.Product.ProductCategory.ProductCategoryId;
import com.develop.internetshop.entities.Product.ProductCategory.ProductCategoryRepository;

/**
* ProductCategoryController
*/
@RestController
@RequestMapping(path = "api/v1/product-category")
public class ProductCategoryController extends BaseApiController<ProductCategory, ProductCategoryId> {
    public ProductCategoryController(ProductCategoryRepository productCategoryRepository) {
        super(productCategoryRepository);
    }
}