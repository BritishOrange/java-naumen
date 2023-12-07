package com.develop.internetshop.entities.Review;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * ReviewRepository
 */
@Repository
public interface ReviewRepository extends CrudRepository<Review, String> {
}
