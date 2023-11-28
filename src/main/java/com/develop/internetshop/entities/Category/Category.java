package com.develop.internetshop.entities.Category;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Category
 */

@Entity
@Table(name = "category_table")

@Getter
@Setter
@ToString

@Schema(description = "Сущность категории")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(description = "Уникальный идентификатор")
    private Long id;

    @Column(name = "parent_id")
    @Schema(description = "Родительская категория")
    private Long parentId;

    @Column(name = "title", length = 75, nullable = false)
    @Schema(description = "Название категории")
    private String title;

    @Column(name = "content")
    @Schema(description = "Описание категории")
    private String content;

    @Column(name = "slug", length = 100)
    @Schema(description = "Название эндпоинта категории")
    private String slug;

    @Column(name = "meta_title", length = 100)
    @Schema(description = "Мета заголовок: как отображается ссылка на страничку с категорией при поиске")
    private String metaTitle;

    public Category() { }

    public Category(Long parentId, String title, String content, String slug, String metaTitle) {
        this.parentId = parentId;
        this.title = title;
        this.content = content;
        this.slug = slug;
        this.metaTitle = metaTitle;
    }
}
