package com.develop.internetshop.controllers.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.develop.internetshop.entities.Product.Product;
import com.develop.internetshop.entities.Product.ProductRepository;

/**
 * ProductController
 */
@RestController
@RequestMapping(path = "api/v1/products")
public class ProductController extends BaseApiController<Product, Long> {
    public ProductController(ProductRepository productRepository) {
        super(productRepository);
    }
}
