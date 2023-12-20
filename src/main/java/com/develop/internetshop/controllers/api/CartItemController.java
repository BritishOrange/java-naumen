package com.develop.internetshop.controllers.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.develop.internetshop.entities.Cart.CartItem.CartItem;
import com.develop.internetshop.entities.Cart.CartItem.CartItemRepository;
import com.develop.internetshop.entities.User.User;
import com.develop.internetshop.services.CartService;

/**
 * CartItemController
 */
@RestController
@RequestMapping(path = "api/v1/cart-item")
public class CartItemController extends BaseApiController<CartItem, String> {
    @Autowired
    private CartService cartService;

    public CartItemController(CartItemRepository cartItemRepository) {
        super(cartItemRepository);
    }

    @GetMapping("/find-user-items")
    public List<CartItem> getCartItemsByUser() {
        List<CartItem> cartItems = cartService.getUserCartItems();
        return cartItems;
    }

    @PostMapping("/post-item")
    public HttpStatus setNewCartItem(
            @RequestParam String id,
            @RequestParam Long quantity) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        if (authentication.getName() == "anonymousUser") {
            return HttpStatus.FORBIDDEN;
        }

        cartService.postCartItem(user, id, quantity);
        return HttpStatus.OK;
    }
}
