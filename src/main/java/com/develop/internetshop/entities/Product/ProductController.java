package com.develop.internetshop.entities.Product;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * // * ProductController
 */

@RestController
@RequestMapping(path = "api/v1/product")
public class ProductController {
    @GetMapping
    public List<Product> getProducts() {
        return List.of(
            new Product(
                null, 
                null, 
                null, 
                null, 
                null, 
                null, 0, 
                0, 
                null, 
                null, 
                null, 
                null, 
                null
            )
        );
    }
}