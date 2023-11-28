package com.develop.internetshop.controllers.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * HomeController
 */
@Controller
public class HomeController {
    @GetMapping(path = "/")
    public String login() {
        return "index";
    }
    
}