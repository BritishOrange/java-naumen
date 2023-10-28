package com.develop.internetshop.entities.Transaction;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;

import java.util.Date;

import com.develop.internetshop.entities.Order.Order;
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
 * Transaction
 */
@Entity
@Table(name = "transaction_table")

@Getter
@ToString
@RequiredArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private final User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private final Order order;

    @Column(name = "code", length = 100, nullable = false)
    private final String code;

    @Column(name = "typr")
    private final TransactionType type;

    @Column(name = "mode")
    private final TransactionMode mode;

    @Column(name = "status")
    private final TransactionStatus status;

    @Column(name = "created_at")
    private final Date createdAt;

    @Column(name = "updated_at")
    private final Date updatedAt;

    @Column(name = "content", length = 500)
    private final Date content;
}
