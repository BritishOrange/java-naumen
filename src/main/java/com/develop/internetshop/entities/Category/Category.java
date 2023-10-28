package com.develop.internetshop.entities.Category;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * Category
 */

@Entity
@Table(name = "category_table")

@Getter
@ToString
@RequiredArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "parent_id")
    private final Long parentId;

    @Column(name = "title", length = 75, nullable = false)
    private final String title;

    @Column(name = "content")
    private final String content;

    @Column(name = "slug", length = 100)
    private final String slug;

    @Column(name = "meta_title", length = 100)
    private final String metaTitle;
}
