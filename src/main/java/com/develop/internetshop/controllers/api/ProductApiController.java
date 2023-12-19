package com.develop.internetshop.controllers.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.develop.internetshop.entities.Product.Product;
import com.develop.internetshop.entities.Product.ProductRepository;
import com.develop.internetshop.services.ProductService;

/**
 * ProductController
 */
@RestController
@RequestMapping(path = "api/v1/products")
public class ProductApiController extends BaseApiController<Product, String> {

    @Autowired
    private ProductService productService;

    public ProductApiController(ProductRepository productRepository) {
        super(productRepository);
    }

    @GetMapping("/find-products")
    public List<Product> findProducts(
        @RequestParam Float lowerPrice,
        @RequestParam Float higherPrice,
        @RequestParam String categoryId,
        @RequestParam Integer compareState,
        @RequestParam String searchText
    ) {
        return productService.findProducts(lowerPrice, higherPrice, categoryId, compareState, searchText);
    }
}
