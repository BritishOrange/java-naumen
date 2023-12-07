package com.develop.internetshop.entities.Tag;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Tag
 */
@Entity
@Table(name = "tag_table")

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "title", length = 75, nullable = false)
    private String title;

    @Column(name = "meta_title", length = 100)
    private String metaTitle;

    @Column(name = "slug", length = 100)
    private String slug;

    @Column(name = "content", length = 500)
    private String content;
}