package com.develop.internetshop.entities.ProductTag;

import java.io.Serializable;

import com.develop.internetshop.entities.Product.Product;
import com.develop.internetshop.entities.Tag.Tag;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
public class ProductTagId implements Serializable {
    private Product product;
    private Tag tag;
}
