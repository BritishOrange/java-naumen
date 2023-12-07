package com.develop.internetshop.controllers.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.develop.internetshop.entities.Cart.Cart;
import com.develop.internetshop.entities.Cart.CartRepository;

/**
* CartController
*/
@RestController
@RequestMapping(path = "api/v1/cart")
public class CartController extends BaseApiController<Cart, String> {
    public CartController(CartRepository cartRepository) {
        super(cartRepository);
    }
}
