package com.develop.internetshop.controllers.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.develop.internetshop.entities.Cart.Cart;
import com.develop.internetshop.entities.Cart.CartRepository;
import com.develop.internetshop.entities.Cart.PostedCart;
import com.develop.internetshop.entities.Cart.PostedCartItem;
import com.develop.internetshop.entities.Cart.CartItem.CartItem;
import com.develop.internetshop.entities.Cart.CartItem.CartItemRepository;
import com.develop.internetshop.entities.Order.Order;
import com.develop.internetshop.entities.Order.OrderRepository;
import com.develop.internetshop.entities.Order.OrderItem.OrderItem;
import com.develop.internetshop.entities.Order.OrderItem.OrderItemRepository;
import com.develop.internetshop.entities.Product.Product;
import com.develop.internetshop.entities.Product.ProductRepository;
import com.develop.internetshop.entities.User.User;
import com.develop.internetshop.services.ProductService;

import org.springframework.web.bind.annotation.RequestBody;

/**
 * HomeController
 */
@Controller
public class HomeController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping(path = "/")
    public String index(Model model) {
        productService.setProducts(model);
        return "index";
    }

    @GetMapping(path = "/category")
    public String category(Model model) {
        productService.setCategoryAttributes(model);
        return "category";
    }

    @GetMapping(path = "/cart")
    public String cart(Model model) {
        List<CartItem> userItemsList;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getName() == "anonymousUser") {
            userItemsList = new ArrayList<CartItem>();
        }
        User user = (User) authentication.getPrincipal();
        Cart userCart = cartRepository.findCartByUser(user);
        userItemsList = cartItemRepository.findCartItemByCart(userCart);
        model.addAttribute("cartItems", userItemsList);
        return "cart";
    }

    @PostMapping(path = "/cart")
    public String makePurchase(@RequestBody PostedCart postedCart) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal(); 

        if (authentication.getName() == "anonymousUser") {
            return "redirect:/";
        }
        
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
                new Date()
            );
            orderItemRepository.save(orderItem);
        }
        
        return "redirect:/confirmation";
    }

    @GetMapping(path = "/confirmation")
    public String confirmation(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal(); 
        List<Order> orderList = orderRepository.findOrderByUser(user);

        Order order  = orderList.get(orderList.size() - 1);

        List<OrderItem> orderItems = orderItemRepository.findOrderItemByOrder(order);
        Float totalSum = 0.0f;

        for (OrderItem orderItem : orderItems) {
            if (orderItem.getQuantity() != 0)
                totalSum += orderItem.getPrice() * orderItem.getQuantity();
        }

        model.addAttribute("orderItems", orderItems.stream().filter(oi -> oi.getQuantity() != 0).toList());
        model.addAttribute("totalSum", totalSum);

        return "confirmation";
    }
}
