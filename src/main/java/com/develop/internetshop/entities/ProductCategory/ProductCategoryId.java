package com.develop.internetshop.entities.ProductCategory;

import java.io.Serializable;

import com.develop.internetshop.entities.Category.Category;
import com.develop.internetshop.entities.Product.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
public class ProductCategoryId implements Serializable {
    private Product product;
    private Category category;
}
