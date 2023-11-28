package com.develop.internetshop.entities.ProductTag;


import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ProductTag
 */
@Entity
@Table(name = "product_tag_table")

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ProductTag {
    @EmbeddedId
    private ProductTagId productTagId;
}