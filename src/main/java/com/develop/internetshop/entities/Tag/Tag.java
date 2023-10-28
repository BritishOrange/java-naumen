package com.develop.internetshop.entities.Tag;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * Tag
 */
@Entity
@Table(name = "tag_table")

@Getter
@ToString
@RequiredArgsConstructor
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title", length = 75, nullable = false)
    private final String title;

    @Column(name = "meta_title", length = 100)
    private final String metaTitle;

    @Column(name = "slug", length = 100)
    private final String slug;

    @Column(name = "content", length = 500)
    private final String content;
}