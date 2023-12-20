package com.develop.internetshop.controllers.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.develop.internetshop.entities.Cart.Cart;
import com.develop.internetshop.entities.Cart.CartRepository;
import com.develop.internetshop.entities.Cart.CartItem.CartItem;
import com.develop.internetshop.entities.Cart.CartItem.CartItemRepository;
import com.develop.internetshop.entities.Product.Product;
import com.develop.internetshop.entities.Product.ProductRepository;
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
    @Autowired
    private ProductRepository productRepository;

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

    @PostMapping("/post-item")
    public HttpStatus setNewCartItem(
        @RequestParam String id,
        @RequestParam Long quantity
    ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        if (authentication.getName() == "anonymousUser") {
            return HttpStatus.FORBIDDEN;
        }

        Cart userCart = cartRepository.findCartByUser(user);
        if (userCart == null) {
            userCart = new Cart(null, user, null, new Date(), new Date());
            cartRepository.save(userCart);
        }
        Product product = productRepository.getReferenceById(id);
        
        CartItem newCartItem = new CartItem(null, product, userCart, quantity, new Date(), new Date());
        for (CartItem cartItem : cartItemRepository.findAll()) {
            if (cartItem.getProduct().getId() == id) {
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                cartItemRepository.save(cartItem);
                return HttpStatus.OK;
            }
        }

        cartItemRepository.save(newCartItem);

        return HttpStatus.OK;
    } 
}
