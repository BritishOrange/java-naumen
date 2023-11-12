package com.develop.internetshop.controllers.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.develop.internetshop.entities.CartItem.CartItem;
import com.develop.internetshop.entities.CartItem.CartItemRepository;

/**
* CartItemController
*/
@RestController
@RequestMapping(path = "api/v1/cart-item")
public class CartItemController extends BaseApiController<CartItem, Long> {
    public CartItemController(CartItemRepository cartItemRepository) {
        super(cartItemRepository);
    }
}
