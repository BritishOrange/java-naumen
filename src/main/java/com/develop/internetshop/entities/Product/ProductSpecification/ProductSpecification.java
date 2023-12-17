package com.develop.internetshop.entities.Product.ProductSpecification;

import java.io.Serializable;

import com.develop.internetshop.entities.Product.Product;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ProductSpecification
 */
@Entity
@Table(name = "product_specification_table")

@Data
@AllArgsConstructor
@NoArgsConstructor

@Schema(description = "Дополнительная информация о товаре")
public class ProductSpecification implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(description = "Уникальный идентификатор")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "value", length = 100, nullable = false)
    private String value;
}