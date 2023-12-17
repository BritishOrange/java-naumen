package com.develop.internetshop.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.develop.internetshop.entities.Category.Category;
import com.develop.internetshop.entities.Category.CategoryRepository;
import com.develop.internetshop.entities.Product.Product;
import com.develop.internetshop.entities.Product.ProductRepository;
import com.develop.internetshop.entities.Product.ProductSpecification.ProductSpecification;
import com.develop.internetshop.entities.Product.ProductSpecification.ProductSpecificationRepository;
import com.develop.internetshop.entities.Review.Review;
import com.develop.internetshop.entities.Review.ReviewRepository;
import com.develop.internetshop.entities.Tag.Tag;
import com.develop.internetshop.entities.Tag.TagRepository;

import org.springframework.ui.Model;


/**
 * // * ProductService
 */

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductSpecificationRepository productSpecificationRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private ReviewRepository reviewRepository;


    public void setProducts(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
    }

    public void setCategoryAttributes(Model model) {
        List<Product> products = productRepository.findAll();
        List<Category> categories = categoryRepository.findAll();
        List<Tag> tags = tagRepository.findAll();
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        model.addAttribute("tags", tags);
    }

    public void setSingleProductAttributes(String id, Model model) {
        Product product = productRepository.findById(id).get();
        List<ProductSpecification> specifications = productSpecificationRepository.findProductSpecificationByProduct(product);
        List<Review> reviews = reviewRepository.findReviewByProduct(product);
        model.addAttribute("product", product);
        model.addAttribute("specifications", specifications);
        model.addAttribute("reviews", reviews);
        setRatingStatistics(reviews, model);
    }

    private void setRatingStatistics(List<Review> reviews, Model model) {
        model.addAttribute("reviewsCount", reviews.size());

        float averageMark = 0.0f;
        int[] marksDistribution = new int[5];
        for (Review review : reviews) {
            averageMark += review.getRating();
            marksDistribution[review.getRating() - 1] += 1;
        }
        if (reviews.size() > 0) averageMark /= reviews.size();

        model.addAttribute("averageMark", averageMark);
        model.addAttribute("marksDistribution", marksDistribution);
    }
}