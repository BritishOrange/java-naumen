package com.develop.internetshop.entities.Product;

import java.util.Date;

import com.develop.internetshop.entities.Category.Category;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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

    @Column(name = "meta_title", length = 200)
    @Schema(description = "Мета заголовок: как отображается ссылка на страничку с товаром при поиске")
    private String metaTitle;

    @Column(name = "slug", length = 100, nullable = false)
    @Schema(description = "Название эндпоинта странички с товаром")
    private String slug;

    @Column(name = "description", length = 1200)
    @Schema(description = "Описание товара")
    private String descriprion;

    @Column(name = "photo_url")
    @Schema(description = "ссылка на изображение")
    private String photoUrl;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @Schema(description = "Категория товара")
    private Category category;

    @Column(name = "sku", nullable = false)
    @Schema(description = "Количество товара на складе")
    private String sku;

    @Column(name = "price", scale = 2, nullable = false)
    @Schema(description = "Цена товара")
    private float price;

    @Column(name = "discount", scale = 2)
    @Schema(description = "Скидка")
    private float discount;

    @Column(name = "created_at", nullable = false)
    @Schema(description = "Время добавления товара")
    private Date createdAt;

    @Column(name = "updated_at")
    @Schema(description = "Время последнего изменения")
    private Date updatedAt;

    @Column(name = "published_at")
    @Schema(description = "Время публикации")
    private Date publishedAt;

    @Column(name = "starts_at")
    @Schema(description = "Начало продаж")
    private Date startsAt;

    @Column(name = "ends_at")
    @Schema(description = "Конец продаж")
    private Date endsAt;
}