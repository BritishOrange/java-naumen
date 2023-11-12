package com.develop.internetshop.controllers.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.develop.internetshop.entities.ProductTag.ProductTag;
import com.develop.internetshop.entities.ProductTag.ProductTagId;
import com.develop.internetshop.entities.ProductTag.ProductTagRepository;

/**
* ProductTagController
*/
@RestController
@RequestMapping(path = "api/v1/product-tag")
public class ProductTagController extends BaseApiController<ProductTag, ProductTagId> {
    public ProductTagController(ProductTagRepository productTagRepository) {
        super(productTagRepository);
    }
}
