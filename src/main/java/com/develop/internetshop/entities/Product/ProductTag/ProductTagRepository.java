package com.develop.internetshop.entities.Product.ProductTag;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * ProductTagRepository
 */
@Repository
public interface ProductTagRepository extends CrudRepository<ProductTag, ProductTagId> {
}
