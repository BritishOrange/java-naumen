package com.develop.internetshop.entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.develop.internetshop.entities.Cart.Cart;
import com.develop.internetshop.entities.Cart.CartRepository;
import com.develop.internetshop.entities.Cart.CartItem.CartItem;
import com.develop.internetshop.entities.Cart.CartItem.CartItemRepository;
import com.develop.internetshop.entities.Category.Category;
import com.develop.internetshop.entities.Category.CategoryRepository;
import com.develop.internetshop.entities.Product.Product;
import com.develop.internetshop.entities.Product.ProductRepository;
import com.develop.internetshop.entities.Product.ProductSpecification.ProductSpecification;
import com.develop.internetshop.entities.Product.ProductSpecification.ProductSpecificationRepository;
import com.develop.internetshop.entities.Review.Review;
import com.develop.internetshop.entities.Review.ReviewRepository;
import com.develop.internetshop.entities.User.User;
import com.develop.internetshop.entities.User.UserRepository;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * LoadMockData
 */
@Component
public class LoadMockData implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductSpecificationRepository productSpecificationRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Adding mocks...");
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        loadUsers();
        loadCategories();
        loadProducts();
        loadVasyaCart();
    }

    private void setPasswordHash(List<User> users) {
        for (User user : users) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
    }

    private void loadVasyaCart() {
        User vasya = userRepository.findUserByEmail("vasya.pupkin@example.ru");
        Cart vasyaCart = new Cart(null, vasya, null, new Date(), new Date());

        Product notebook = productRepository.findProductByTitle("Ноутбук NoteBest 4K");
        Product smartphone = productRepository.findProductByTitle("Смартфон Red Fox B2");

        CartItem notebookItem = new CartItem(null, notebook, vasyaCart, 1l, new Date(), new Date());
        CartItem smartphoneItem = new CartItem(null, smartphone, vasyaCart, 2l, new Date(), new Date());

        cartRepository.save(vasyaCart);
        cartItemRepository.saveAll(List.of(notebookItem, smartphoneItem));
    }

    private void loadUsers() throws StreamReadException, DatabindException, IOException {
        File file = new File("src/main/resources/mocks/users.json");
        List<User> users = objectMapper.readValue(file, new TypeReference<>() {
        });
        setPasswordHash(users);
        userRepository.saveAll(users);
    }

    private void loadCategories() throws StreamReadException, DatabindException, IOException {
        File file = new File("src/main/resources/mocks/categories.json");
        List<Category> categories = objectMapper.readValue(file, new TypeReference<>() {
        });
        categoryRepository.saveAll(categories);
    }

    private void loadProducts() throws StreamReadException, DatabindException, IOException {
        loadProductCategory("src/main/resources/mocks/products/notebooks.json", "Ноутбуки");
        loadProductCategory("src/main/resources/mocks/products/smartphones.json", "Смартфоны");
    }

    private void loadProductCategory(String fileName, String categoryName)
            throws StreamReadException, DatabindException, IOException {
        File file = new File(fileName);
        List<Product> products = objectMapper.readValue(file, new TypeReference<List<Product>>() {
        });
        Category notebookCategory = categoryRepository.findCategoryByTitle(categoryName);

        for (Product product : products) {
            product.setCategory(notebookCategory);
            List<ProductSpecification> specifications = product.getSpecifications();
            List<Review> reviews = product.getReviews();

            for (ProductSpecification productSpecification : specifications) {
                productSpecification.setProduct(product);
            }

            User user = userRepository.findUserByEmail("alisa.dolgopolova@example.ru");

            for (Review review : reviews) {
                review.setProduct(product);
                review.setUser(user);
            }

            productRepository.save(product);
            productSpecificationRepository.saveAll(specifications);
            if (reviews.size() > 0)
                reviewRepository.saveAll(reviews);
        }
    }
}