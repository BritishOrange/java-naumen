package com.develop.internetshop.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.develop.internetshop.entities.Cart.Cart;
import com.develop.internetshop.entities.Cart.CartRepository;
import com.develop.internetshop.entities.Cart.CartItem.CartItem;
import com.develop.internetshop.entities.Cart.CartItem.CartItemRepository;
import com.develop.internetshop.entities.Product.Product;
import com.develop.internetshop.entities.Product.ProductRepository;
import com.develop.internetshop.entities.User.User;

/**
 * CartService
 */
@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ProductRepository productRepository;

    public List<CartItem> getUserCartItems() {
        List<CartItem> userItemsList;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getName() == "anonymousUser")
            userItemsList = new ArrayList<CartItem>();

        User user = (User) authentication.getPrincipal();
        Cart userCart = cartRepository.findCartByUser(user);
        userItemsList = cartItemRepository.findCartItemByCart(userCart);

        return userItemsList;
    }

    public void postCartItem(User user, String id, Long quantity) {
        Cart userCart = cartRepository.findCartByUser(user);
        if (userCart == null) {
            userCart = new Cart(null, user);
            cartRepository.save(userCart);
        }
        Product product = productRepository.getReferenceById(id);

        CartItem newCartItem = new CartItem(null, product, userCart, quantity);
        for (CartItem cartItem : cartItemRepository.findAll()) {
            if (cartItem.getProduct().getId() == id) {
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                cartItemRepository.save(cartItem);
                return;
            }
        }

        cartItemRepository.save(newCartItem);
    }
}
