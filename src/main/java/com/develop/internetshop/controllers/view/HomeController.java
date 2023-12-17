package com.develop.internetshop.controllers.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.develop.internetshop.services.ProductService;

/**
 * HomeController
 */
@Controller
public class HomeController {
    
    @Autowired
    private ProductService productService;

    @GetMapping(path = "/")
    public String index(Model model) {
        productService.setProducts(model);
        return "index";
    }

    @GetMapping(path = "/category")
    public String category(Model model) {
        productService.setCategoryAttributes(model);
        return "category";
    }

    @GetMapping(path = "/cart")
    public String cart() {
        return "cart";
    }

    @GetMapping(path = "/confirmation")
    public String confirmation() {
        return "confirmation";
    }

    @GetMapping(path = "/product/{id}")
    public String singleProduct(@PathVariable String id, Model model) {
        productService.setSingleProductAttributes(id, model);
        return "product";
    }
}
