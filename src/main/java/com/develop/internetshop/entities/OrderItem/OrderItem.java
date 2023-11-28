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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * OrderItem
 */
@Entity
@Table(name = "order_item_table")

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "sku", nullable = false)
    private String sku;

    @Column(name = "price", scale = 2)
    private float price;

    @Column(name = "discount", scale = 2)
    private float discount;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "content", length = 500)
    private Date content;
}
