package com.develop.internetshop.entities.Order.OrderItem;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.develop.internetshop.entities.Order.Order;

/**
 * OrderItemRepository
 */
public interface OrderItemRepository extends CrudRepository<OrderItem, String> {
    public List<OrderItem> findOrderItemByOrder(Order order);
}