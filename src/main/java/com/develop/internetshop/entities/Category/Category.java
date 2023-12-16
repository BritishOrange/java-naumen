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
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(description = "Уникальный идентификатор")
    private String id;

    @Column(name = "parent_id")
    @Schema(description = "Родительская категория")
    private Long parentId;

    @Column(name = "title", length = 75, nullable = false)
    @Schema(description = "Название категории")
    private String title;

    public Category() { }

    public Category(Long parentId, String title) {
        this.parentId = parentId;
        this.title = title;
    }
}
