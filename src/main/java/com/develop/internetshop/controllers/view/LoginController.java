package com.develop.internetshop.controllers.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * LoginController
 */

@Controller
public class LoginController {
    @GetMapping(path = "/login")
    public String login() {
        return "login";
    }

    @PostMapping(path = "/login")
    public String tryLogin(
        @RequestParam("name") String username,
        @RequestParam("password") String password
    ) {
        return "redirect:/";
    }

    @GetMapping(path = "/register")
    public String register() {
        return "register";
    }
}
