package com.develop.internetshop.entities.OrderItem;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;

import java.util.Date;

import com.develop.internetshop.entities.Order.Order;
import com.develop.internetshop.entities.Product.Product;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * OrderItem
 */
@Entity
@Table(name = "order_item_table")

@Getter
@ToString
@RequiredArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private final Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    private final Order order;

    @Column(name = "sku", nullable = false)
    private final String sku;

    @Column(name = "price", scale = 2)
    private final float price;

    @Column(name = "discount", scale = 2)
    private final float discount;

    @Column(name = "created_at")
    private final Date createdAt;

    @Column(name = "updated_at")
    private final Date updatedAt;

    @Column(name = "content", length = 500)
    private final Date content;
}
