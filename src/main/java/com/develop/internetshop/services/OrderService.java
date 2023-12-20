package com.develop.internetshop.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.develop.internetshop.entities.Cart.PostedCart;
import com.develop.internetshop.entities.Cart.PostedCartItem;
import com.develop.internetshop.entities.Order.Order;
import com.develop.internetshop.entities.Order.OrderRepository;
import com.develop.internetshop.entities.Order.OrderItem.OrderItem;
import com.develop.internetshop.entities.Order.OrderItem.OrderItemRepository;
import com.develop.internetshop.entities.Product.Product;
import com.develop.internetshop.entities.Product.ProductRepository;
import com.develop.internetshop.entities.User.User;
import org.springframework.stereotype.Service;

/**
 * CartService
 */
@Service
public class OrderService {
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;

    public List<OrderItem> getOrderItems() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        List<Order> orderList = orderRepository.findOrderByUser(user);
        Order order = orderList.get(orderList.size() - 1);
        List<OrderItem> orderItems = orderItemRepository.findOrderItemByOrder(order);

        return orderItems.stream().filter(oi -> oi.getQuantity() != 0).toList();
    }

    public Float getTotalSum() {
        List<OrderItem> orderItems = getOrderItems();
        Float totalSum = 0.0f;
        for (OrderItem orderItem : orderItems) {
            if (orderItem.getQuantity() != 0)
                totalSum += orderItem.getProduct().getPrice() * orderItem.getQuantity();
        }

        return totalSum;
    }

    public void postOrder(PostedCart postedCart, User user) {
        Float totalSum = 0.0f;
        Order newOrder = new Order(null, user, null, new Date(), new Date());
        orderRepository.save(newOrder);
        for (PostedCartItem postedCartItem : postedCart.getPostedCartItems()) {
            Product product = productRepository.findById(postedCartItem.getId()).get();
            totalSum += product.getPrice();
            OrderItem orderItem = new OrderItem(
                    null,
                    product,
                    newOrder,
                    postedCartItem.getQuantity(),
                    totalSum,
                    new Date(),
                    new Date());
            orderItemRepository.save(orderItem);
        }
    }
}
