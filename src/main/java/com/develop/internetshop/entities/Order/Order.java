package com.develop.internetshop.entities.Order;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;

import java.util.Date;

import com.develop.internetshop.entities.User.User;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * Order
 */
@Entity
@Table(name = "order_table")

@Getter
@ToString
@RequiredArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private final User user;

    @Column(name = "session_id", length = 100)
    private final String sessionId;

    @Column(name = "token", length = 100)
    private final String Token;

    @Column(name = "status", length = 100)
    private final OrderStatus status;

    @Column(name = "sub_total", scale = 2)
    private final float subTotal;

    @Column(name = "item_discount", scale = 2)
    private final float itemDiscount;

    @Column(name = "total", scale = 2)
    private final float total;

    @Column(name = "created_at")
    private final Date createdAt;

    @Column(name = "updated_at")
    private final Date updatedAt;

    @Column(name = "content")
    private final String content;

}
