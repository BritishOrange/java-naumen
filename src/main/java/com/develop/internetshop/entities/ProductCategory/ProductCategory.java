package com.develop.internetshop.entities.ProductCategory;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * ProductCategory
 */

@Entity
@Table(name = "product_category_table")


@Getter
@ToString
@AllArgsConstructor
public class ProductCategory {

    @EmbeddedId
    private ProductCategoryId productCategoryId;
}