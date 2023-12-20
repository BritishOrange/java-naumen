package com.develop.internetshop.controllers.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.develop.internetshop.entities.Cart.Cart;
import com.develop.internetshop.entities.Cart.CartRepository;
import com.develop.internetshop.entities.Cart.CartItem.CartItem;
import com.develop.internetshop.entities.Cart.CartItem.CartItemRepository;
import com.develop.internetshop.entities.User.User;

/**
* CartItemController
*/
@RestController
@RequestMapping(path = "api/v1/cart-item")
public class CartItemController extends BaseApiController<CartItem, String> {
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private CartRepository cartRepository;

    public CartItemController(CartItemRepository cartItemRepository) {
        super(cartItemRepository);
    }

    @GetMapping("/find-user-items")
    public List<CartItem> getCartItemsByUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getName() == "anonymousUser") {
            return new ArrayList<CartItem>();
        }
        User user = (User) authentication.getPrincipal();
        Cart userCart = cartRepository.findCartByUser(user);
        List<CartItem> cartItems = cartItemRepository.findCartItemByCart(userCart);

        return cartItems;
    }
}
