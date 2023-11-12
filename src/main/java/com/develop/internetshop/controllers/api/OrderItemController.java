package com.develop.internetshop.controllers.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.develop.internetshop.entities.OrderItem.OrderItem;
import com.develop.internetshop.entities.OrderItem.OrderItemRepository;

/**
* OrderItemController
*/
@RestController
@RequestMapping(path = "api/v1/order-item")
public class OrderItemController extends BaseApiController<OrderItem, Long> {
    public OrderItemController(OrderItemRepository orderItemRepository) {
        super(orderItemRepository);
    }
}
