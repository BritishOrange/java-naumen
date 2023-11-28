package com.develop.internetshop.entities.Category;

import java.util.List;

import org.springframework.stereotype.Component;

import com.develop.internetshop.entities.Product.Product;

import jakarta.annotation.PostConstruct;

/**
 * CategoryDataLoader
 */
@Component
public class CategoryDataLoader {
    private final CategoryRepository categoryRepository;
    
    public CategoryDataLoader(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    
    @PostConstruct
    public void loadData() {
        categoryRepository.saveAll(
            List.of(
                new Category(
                    -1l, 
                    "Ноутбуки", 
                    "Выбор ноутбуков на любой вкус", 
                    "noutbuki-category", 
                    "Ноутбуки купить в интернет магазине ТехноБазар. Ноутбуки цены, большой каталог, новинки"
                ),
                new Category(
                    -1l, 
                    "Смарт часы", 
                    "Выбор смарт-часов на любой вкус", 
                    "chasi-category", 
                    "Часы купить в интернет магазине ТехноБазар"
                ),
                new Category(
                    -1l, 
                    "Холодильники", 
                    "Выбор холодильников на любой вкус", 
                    "holod-category", 
                    "Холодильники купить в интернет магазине ТехноБазар"
                )
            )
        );
    }
}
