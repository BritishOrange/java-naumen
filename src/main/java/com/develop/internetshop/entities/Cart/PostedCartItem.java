package com.develop.internetshop.entities.Cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * PostedCart
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostedCartItem {
    private String id;
    private Long quantity;
}
