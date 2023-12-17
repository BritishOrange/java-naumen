package com.develop.internetshop.entities.Product.ProductSpecification;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.develop.internetshop.entities.Product.Product;

/**
 * ProductSpecificationRepository
 */
@Repository
public interface ProductSpecificationRepository extends JpaRepository<ProductSpecification, String> {
    public List<ProductSpecification> findProductSpecificationByProduct(Product product);
}