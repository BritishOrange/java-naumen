package com.develop.internetshop.controllers.view;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.develop.internetshop.services.OrderService;
import com.develop.internetshop.services.ProductService;

/**
 * HomeController
 */
@Controller
public class HomeController {

    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;
    
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

    @GetMapping(path = "/confirmation")
    public String confirmation(Model model) {
        model.addAttribute("orderItems", orderService.getOrderItems());
        model.addAttribute("totalSum", orderService.getTotalSum());
        return "confirmation";
    }
}
