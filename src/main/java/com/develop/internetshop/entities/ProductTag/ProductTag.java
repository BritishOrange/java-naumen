package com.develop.internetshop.entities.ProductTag;

import com.develop.internetshop.entities.Product.Product;
import com.develop.internetshop.entities.Tag.Tag;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * ProductTag
 */
@Entity
@Table(name = "product_tag_table")
@IdClass(ProductTagId.class)

@Getter
@ToString
@AllArgsConstructor
public class ProductTag {
    @Id
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Id
    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;
}
