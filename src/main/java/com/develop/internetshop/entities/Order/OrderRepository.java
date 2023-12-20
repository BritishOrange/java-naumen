package com.develop.internetshop.entities.Order;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.develop.internetshop.entities.User.User;

/**
 * OrderRepository
 */
public interface OrderRepository extends CrudRepository<Order, String> {
    public List<Order> findOrderByUser(User user);
}