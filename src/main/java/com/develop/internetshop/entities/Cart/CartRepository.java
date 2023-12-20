package com.develop.internetshop.entities.Cart;

import org.springframework.data.repository.CrudRepository;

import com.develop.internetshop.entities.User.User;

/**
 * CartRepository
 */
public interface CartRepository extends CrudRepository<Cart, String> {
    public Cart findCartByUser(User user);
}
