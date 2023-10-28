package com.develop.internetshop.entities.ProductCategory;

import com.develop.internetshop.entities.Category.Category;
import com.develop.internetshop.entities.Product.Product;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * ProductCategory
 */

@Entity
@Table(name = "product_category_table")
@IdClass(ProductCategoryId.class)

@Getter
@ToString
@RequiredArgsConstructor

public class ProductCategory {
    @Id
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Id
    @ManyToOne
    @JoinColumn(name = "category_id") 
    private Category category;
}