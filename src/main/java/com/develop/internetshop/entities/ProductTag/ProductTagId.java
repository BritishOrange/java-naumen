package com.develop.internetshop.entities.ProductTag;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@ToString
@Getter

@Embeddable
public class ProductTagId implements Serializable {
    private Long product;
    private Long tag;
}
