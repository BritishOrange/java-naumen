package com.develop.internetshop.entities.ProductTag;


import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * ProductTag
 */
@Entity
@Table(name = "product_tag_table")

@Getter
@ToString
@AllArgsConstructor
public class ProductTag {
    @EmbeddedId
    private ProductTagId productTagId;
}