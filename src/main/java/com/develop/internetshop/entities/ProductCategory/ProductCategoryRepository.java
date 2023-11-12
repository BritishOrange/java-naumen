package com.develop.internetshop.entities.ProductCategory;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * ProductCategoryRepository
 */
@Repository
public interface ProductCategoryRepository extends CrudRepository<ProductCategory, ProductCategoryId> {
}