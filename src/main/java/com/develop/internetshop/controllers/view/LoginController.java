package com.develop.internetshop.controllers.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.develop.internetshop.services.RegisterService;

/**
 * LoginController
 */

@Controller
public class LoginController {
    @Autowired
    private RegisterService registerService;

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
    public String register(Model model) {
        model.addAttribute("errorMessage", "");
        return "register";
    }

    @PostMapping(path = "/register")
    public String postRegister(
        @RequestParam String name,
        @RequestParam String email,
        @RequestParam String password,
        @RequestParam String confirmPassword,
        Model model
    ) {
        String validationResult = registerService.validatePostValues(name, email, password, confirmPassword);
        if (validationResult != "") {
            model.addAttribute("errorMessage", validationResult);
            return "register";
        }
        registerService.registerUser(name, email, password, confirmPassword);
        return "redirect:/";
    }
}
