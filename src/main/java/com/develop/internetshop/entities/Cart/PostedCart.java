package com.develop.internetshop.entities.Cart;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * PostedCart
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostedCart {
    private ArrayList<PostedCartItem> postedCartItems;
}
