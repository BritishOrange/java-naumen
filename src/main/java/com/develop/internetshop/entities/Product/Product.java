package com.develop.internetshop.entities.Product;

import java.util.List;

import com.develop.internetshop.entities.Category.Category;
import com.develop.internetshop.entities.Product.ProductSpecification.ProductSpecification;
import com.develop.internetshop.entities.Review.Review;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Product
 */

@Entity
@Table(name = "product_table")

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Schema(description = "Сущность товара")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(description = "Уникальный идентификатор")
    private String id;

    @Column(name = "title", length = 75, nullable = false)
    @Schema(description = "Название товара")
    private String title;

    @Column(name = "description", length = 10000)
    @Schema(description = "Описание товара")
    private String description;

    @OneToMany(fetch = FetchType.LAZY)
    @Schema(description = "Характеристики товара")
    @Transient
    private List<ProductSpecification> specifications;

    @OneToMany(fetch = FetchType.LAZY)
    @Schema(description = "Отзывы о товаре")
    @Transient
    private List<Review> reviews;

    @Column(name = "photo_url")
    @Schema(description = "ссылка на изображение")
    private String photoUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @Schema(description = "Категория товара")
    private Category category;

    @Column(name = "sku", nullable = false)
    @Schema(description = "Количество товара на складе")
    private Long sku;

    @Column(name = "price", scale = 2, nullable = false)
    @Schema(description = "Цена товара")
    private float price;
}