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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Order
 */
@Entity
@Table(name = "order_table")

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(name = "session_id", length = 100)
    private String sessionId;

    @Column(name = "token", length = 100)
    private String Token;

    @Column(name = "status", length = 100)
    private OrderStatus status;

    @Column(name = "sub_total", scale = 2)
    private float subTotal;

    @Column(name = "item_discount", scale = 2)
    private float itemDiscount;

    @Column(name = "total", scale = 2)
    private float total;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "content")
    private String content;

}
