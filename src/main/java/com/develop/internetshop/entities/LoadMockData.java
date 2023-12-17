package com.develop.internetshop.entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

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
import com.develop.internetshop.entities.User.UserRepository;
import com.develop.internetshop.entities.User.UserType;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletContext;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
    private TagRepository tagRepository;
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
        // loadTags();
        // loadReviews();
    }

    private void setPasswordHash(List<User> users) {
        for (User user : users) {
            user.setPasswordHash(passwordEncoder.encode(user.getPassword()));
        }
    }

    private void loadUsers() throws StreamReadException, DatabindException, IOException {
        File file = new File("src/main/resources/mocks/users.json");
        List<User> users = objectMapper.readValue(file, new TypeReference<>(){});
        setPasswordHash(users);
        userRepository.saveAll(users);
    }

    private void loadCategories() throws StreamReadException, DatabindException, IOException {
        File file = new File("src/main/resources/mocks/categories.json");
        List<Category> categories = objectMapper.readValue(file, new TypeReference<>(){});
        categoryRepository.saveAll(categories);
    }

    private void loadProducts() throws StreamReadException, DatabindException, IOException {
        File file = new File("src/main/resources/mocks/products/notebooks.json");
        List<Product> products = objectMapper.readValue(file, new TypeReference<List<Product>>(){});
        Category notebookCategory = categoryRepository.findCategoryByTitle("Ноутбуки");

        for (Product product : products) {
            product.setCategory(notebookCategory);
            List<ProductSpecification> specifications = product.getSpecifications();
            List<Review> reviews = product.getReview();

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
            reviewRepository.saveAll(reviews);
        }
    }

    // private void loadTags() throws IOException, CsvException {
    //     List<String[]> rawData = readData("src\\main\\resources\\mocks\\tags.csv");

    //     List<Tag> tags = new ArrayList<Tag>();
    //     for (int i = 1; i < rawData.size(); i++) {
    //         String[] row = rawData.get(i);
    //         tags.add(new Tag(null, row[0], row[1], row[2], row[3]));
    //     }

    //     tagRepository.saveAll(tags);
    // }

    // private void loadReviews() throws IOException, CsvException {
    //     List<String[]> rawData = readData("src\\main\\resources\\mocks\\reviews.csv");

    //     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

    //     List<User> users = new ArrayList<User>();
    //     List<Product> products = new ArrayList<Product>();

    //     long usersNum = userRepository.count();
    //     long productsNum = productRepository.count();

    //     Random random = new Random();

    //     userRepository.findAll().forEach(u -> users.add(u));
    //     productRepository.findAll().forEach(p -> products.add(p));

    //     List<Review> reviews = new ArrayList<Review>();
    //     for (int i = 1; i < rawData.size(); i++) {
    //         String[] row = rawData.get(i);
    //         reviews.add(new Review(
    //             null, 
    //             users.get(random.nextInt((int) usersNum)), 
    //             products.get(random.nextInt((int) productsNum)), 
    //             row[2], 
    //             Byte.parseByte(row[0]), 
    //             Date.from(LocalDateTime.parse(row[1], formatter).atZone(ZoneId.systemDefault()).toInstant())
    //         ));
    //     }

    //     reviewRepository.saveAll(reviews);
    // }
}