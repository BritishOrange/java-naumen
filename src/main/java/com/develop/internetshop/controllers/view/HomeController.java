package com.develop.internetshop.controllers.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.develop.internetshop.entities.Category.Category;
import com.develop.internetshop.entities.Category.CategoryRepository;
import com.develop.internetshop.entities.Product.Product;
import com.develop.internetshop.entities.Product.ProductRepository;
import com.develop.internetshop.entities.Product.ProductSpecification.ProductSpecification;
import com.develop.internetshop.entities.Product.ProductSpecification.ProductSpecificationRepository;
import com.develop.internetshop.entities.Tag.Tag;
import com.develop.internetshop.entities.Tag.TagRepository;

/**
 * HomeController
 */
@Controller
public class HomeController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductSpecificationRepository productSpecificationRepository;
    @Autowired
    private TagRepository tagRepository;

    @GetMapping(path = "/")
    public String index(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "index";
    }

    @GetMapping(path = "/category")
    public String category(Model model) {
        List<Product> products = productRepository.findAll();
        List<Category> categories = categoryRepository.findAll();
        List<Tag> tags = tagRepository.findAll();
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        model.addAttribute("tags", tags);
        return "category";
    }

    @GetMapping(path = "/cart")
    public String cart() {
        return "cart";
    }

    @GetMapping(path = "/product/{id}")
    public String singleProduct(@PathVariable String id, Model model) {
        Product product = productRepository.findById(id).get();
        List<ProductSpecification> specifications = productSpecificationRepository.findProductSpecificationByProduct(product);
        model.addAttribute("product", product);
        model.addAttribute("specifications", specifications);
        return "product";
    }
}
