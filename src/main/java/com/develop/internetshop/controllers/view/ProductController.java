package com.develop.internetshop.controllers.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.develop.internetshop.services.ProductService;

/**
 * ProductController
 */
@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping(path = "/{id}")
    public String singleProduct(@PathVariable String id, Model model) {
        productService.setSingleProductAttributes(id, model);
        return "product";
    }

    @PostMapping(path = "/{id}/add-review")
    public String addReview(
        @PathVariable String id,
        @RequestParam("rating") int rating,
        @RequestParam("textarea") String text
    ) {
        productService.setNewReview(id, rating, text);
        return "redirect:/";
    }
}
