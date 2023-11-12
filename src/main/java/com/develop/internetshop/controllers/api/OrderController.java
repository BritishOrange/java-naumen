package com.develop.internetshop.controllers.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.develop.internetshop.entities.Order.Order;
import com.develop.internetshop.entities.Order.OrderRepository;

/**
* OrderController
*/
@RestController
@RequestMapping(path = "api/v1/order")
public class OrderController extends BaseApiController<Order, Long> {
    public OrderController(OrderRepository orderRepository) {
        super(orderRepository);
    }
}

