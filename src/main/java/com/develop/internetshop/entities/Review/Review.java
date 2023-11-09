package com.develop.internetshop.entities.Review;

import java.util.Date;

import com.develop.internetshop.entities.Product.Product;
import com.develop.internetshop.entities.User.User;

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
 * Review
 */
@Entity
@Table(name = "review_table")

@Getter
@RequiredArgsConstructor
@ToString
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private final User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private final Product product;

    @Column(name = "content", length = 500)
    private final String content;

    @Column(name = "rating")
    private final byte rating;

    @Column(name = "published_at", nullable = false)
    private final Date publishedAt;
}