package com.develop.internetshop.entities.Product.ProductCategory;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ProductCategory
 */

@Entity
@Table(name = "product_category_table")

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ProductCategory {

    @EmbeddedId
    private ProductCategoryId productCategoryId;
}