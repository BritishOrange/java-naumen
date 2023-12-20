package com.develop.internetshop.entities.Cart.CartItem;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.develop.internetshop.entities.Cart.Cart;

/**
 * CartItemRepository
 */
public interface CartItemRepository extends CrudRepository<CartItem, String> {
    public List<CartItem> findCartItemByCart(Cart cart);
}