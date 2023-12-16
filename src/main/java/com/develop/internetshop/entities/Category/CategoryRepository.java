package com.develop.internetshop.entities.Category;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * CategoryRepository
 */
public interface CategoryRepository extends JpaRepository<Category, String> {
    @Query(
        value = "SELECT * FROM category_table ut WHERE ut.title = ?1", 
        nativeQuery = true
    )
    public Category findCategoryByTitle(String title);
}