package com.develop.internetshop.entities.Category;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * CategoryRepository
 */
public interface CategoryRepository extends CrudRepository<Category, Long> {
    @Query(
        value = "SELECT * FROM user_table ut WHERE ut.title = ?1", 
        nativeQuery = true
    )
    public Category findCategoryByTitle(String title);
}