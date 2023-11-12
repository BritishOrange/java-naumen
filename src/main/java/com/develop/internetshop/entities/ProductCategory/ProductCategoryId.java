package com.develop.internetshop.entities.ProductCategory;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter

@Embeddable
public class ProductCategoryId implements Serializable {
    private Long product;
    private Long category;
}
