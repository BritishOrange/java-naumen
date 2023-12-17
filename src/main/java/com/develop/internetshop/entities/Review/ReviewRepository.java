package com.develop.internetshop.entities.Review;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.develop.internetshop.entities.Product.Product;

/**
 * ReviewRepository
 */
@Repository
public interface ReviewRepository extends CrudRepository<Review, String> {
    public List<Review> findReviewByProduct(Product product);
}
