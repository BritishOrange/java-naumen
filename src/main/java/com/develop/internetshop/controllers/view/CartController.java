package com.develop.internetshop.controllers.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.develop.internetshop.entities.Cart.PostedCart;
import com.develop.internetshop.entities.Cart.CartItem.CartItem;
import com.develop.internetshop.entities.User.User;
import com.develop.internetshop.services.CartService;
import com.develop.internetshop.services.OrderService;

/**
 * CartController
 */
@Controller
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderService orderService;
    
    @GetMapping(path = "/cart")
    public String cart(Model model) {
        List<CartItem> userItemsList = cartService.getUserCartItems();
        model.addAttribute("cartItems", userItemsList);
        return "cart";
    }

    @PostMapping(path = "/cart")
    public String makePurchase(@RequestBody PostedCart postedCart) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        if (authentication.getName() == "anonymousUser") {
            return "redirect:/";
        }

        orderService.postOrder(postedCart, user);
        return "redirect:/confirmation";
    }
    
}
