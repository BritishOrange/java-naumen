package com.develop.internetshop.entities.Cart;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;

import java.util.Date;

import com.develop.internetshop.entities.User.User;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * Cart
 */
@Entity
@Table(name = "cart_table")

@Getter
@ToString
@RequiredArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private final User user;

    @Column(name = "session_id", length = 100)
    private final String sessionId;

    @Column(name = "token", length = 100)
    private final String Token;

    @Column(name = "status")
    private final CartStatus status;

    @Column(name = "created_at")
    private final Date createdAt;

    @Column(name = "updated_at")
    private final Date updatedAt;

    @Column(name = "content")
    private final String content;
}
