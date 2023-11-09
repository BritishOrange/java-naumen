package com.develop.internetshop.entities.Product;

import java.util.Date;

import com.develop.internetshop.entities.Category.Category;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * Product
 */

@Entity
@Table(name = "product_table")

@Getter
@ToString
@RequiredArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title", length = 75, nullable = false)
    private final String title;

    @Column(name = "meta_title", length = 100)
    private final String metaTitle;

    @Column(name = "slug", length = 100, nullable = false)
    private final String slug;

    @Column(name = "description", length = 200)
    private final String descriprion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private final Category category;

    @Column(name = "sku", nullable = false)
    private final String sku;

    @Column(name = "price", scale = 2, nullable = false)
    private final float price;

    @Column(name = "discount", scale = 2)
    private final float discount;

    @Column(name = "created_at", nullable = false)
    private final Date createdAt;

    @Column(name = "updated_at")
    private final Date updatedAt;

    @Column(name = "published_at")
    private final Date publishedAt;

    @Column(name = "starts_at")
    private final Date startsAt;

    @Column(name = "ends_at")
    private final Date endsAt;
}