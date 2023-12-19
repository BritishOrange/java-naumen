package com.develop.internetshop.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.develop.internetshop.entities.User.User;

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

    public Comparator<Product> reviewsComarator = new Comparator<Product>() {
        @Override
        public int compare(Product a, Product b) {
            Float averageMarkA = getAverageMarkDistribution(reviewRepository.findReviewByProduct(a), new int[5]);
            Float averageMarkB = getAverageMarkDistribution(reviewRepository.findReviewByProduct(b), new int[5]);
            return Float.compare(averageMarkB, averageMarkA);
        }
    };

    public Comparator<Product> priceComparator = new Comparator<Product>() {
        @Override
        public int compare(Product a, Product b) {
            Float aPrice = a.getPrice();
            Float bPrice= b.getPrice();
            return Float.compare(aPrice, bPrice);
        }
    };

    public List<Product> findProducts(
        Float lowerPrice, 
        Float higherPrice, 
        String categoryId, 
        Integer compareState,
        String searchText
    ) {
        if (lowerPrice == null || higherPrice == null) {
            return new ArrayList<Product>();
        }

        List<Product> products = productRepository.findAll().stream()
                .filter(p -> filterProduct(lowerPrice, higherPrice, categoryId, p, searchText)).toList();

        if (compareState == 2)
            products = sortByPopularity(products);
        else if (compareState == 3)
            products = sortByPrice(products);
        return products;
    }

    private boolean filterProduct(Float lowerPrice, Float higherPrice, String categoryId, Product product, String searchText) {
        String productCategoryId = product.getCategory().getId();
        boolean res = ((lowerPrice <= product.getPrice())
                && (product.getPrice() <= higherPrice)
                && (categoryId == "" || productCategoryId.compareTo(categoryId) == 0)
                && (searchText == "" || product.getTitle().contains(searchText))
        );
        return res;
    }

    private List<Product> sortByPopularity(List<Product> products) {
        return products.stream().sorted(reviewsComarator).toList();
    }

    private List<Product> sortByPrice(List<Product> products) {
        return products.stream().sorted(priceComparator).toList();
    }

    public void setProducts(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
    }

    public void setCategoryAttributes(Model model) {
        List<Product> products;
        products = productRepository.findAll();
        List<Category> categories = categoryRepository.findAll();
        List<Tag> tags = tagRepository.findAll();
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        model.addAttribute("tags", tags);
    }

    public void setSingleProductAttributes(String id, Model model) {
        Product product = productRepository.findById(id).get();
        List<ProductSpecification> specifications = productSpecificationRepository
                .findProductSpecificationByProduct(product);
        List<Review> reviews = reviewRepository.findReviewByProduct(product);
        model.addAttribute("product", product);
        model.addAttribute("specifications", specifications);
        model.addAttribute("reviews", reviews);
        setRatingStatistics(reviews, model);
        setUserIsAuthenticated(model);
    }

    public void setNewReview(String id, int rating, String text) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        Product product = productRepository.getReferenceById(id);
        Review newReview = new Review(null, user, product, text, rating, new Date());
        reviewRepository.save(newReview);
    }

    private void setUserIsAuthenticated(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isAuthenticated", authentication.getName() != "anonymousUser");
    }

    private void setRatingStatistics(List<Review> reviews, Model model) {
        model.addAttribute("reviewsCount", reviews.size());
        int[] marksDistribution = new int[5];
        Float averageMark = getAverageMarkDistribution(reviews, marksDistribution);

        model.addAttribute("averageMark", String.format("%.1f", averageMark));
        model.addAttribute("marksDistribution", marksDistribution);
    }

    private Float getAverageMarkDistribution(List<Review> reviews, int[] marksDistribution) {
        if (reviews == null) {
            return 0.0f;
        }
        float averageMark = 0.0f;

        for (Review review : reviews) {
            averageMark += review.getRating();
            marksDistribution[review.getRating() - 1] += 1;
        }
        if (reviews.size() > 0)
            averageMark /= reviews.size();

        return averageMark;
    }
}