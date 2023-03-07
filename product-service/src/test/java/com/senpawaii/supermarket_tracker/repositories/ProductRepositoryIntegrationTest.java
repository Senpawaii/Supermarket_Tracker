package com.senpawaii.supermarket_tracker.repositories;

import org.bson.assertions.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;
import com.senpawaii.supermarket_tracker.models.Product;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataMongoTest
@ExtendWith(SpringExtension.class)
public class ProductRepositoryIntegrationTest {

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void cleanUpDatabase() {
        productRepository.deleteAll();
    }

    @Test
    void bootstrapTestDataWithMongoTemplate() {
        Product product = new Product("Test Product", "Test Brand", 10.0, "Test Category");

        productRepository.insert(product);

        assertEquals("Test Product", product.getName());
        assertEquals("Test Brand", product.getBrand());
        assertEquals(10.0, product.getPrice());
        assertEquals("Test Category", product.getCategory());
    }

    @Test
    void createProductAndFindItByName() {
        // Given
        Product product = new Product("Test Product", "Test Brand", 10.0, "Test Category");
        productRepository.save(product);

        // When
        Product foundProduct = productRepository.findByName("Test Product");

        // Then
        Assertions.assertNotNull(foundProduct);
        assertEquals("Test Product", foundProduct.getName());
        assertEquals("Test Brand", foundProduct.getBrand());
        assertEquals(10.0, foundProduct.getPrice());
        assertEquals("Test Category", foundProduct.getCategory());
    }

    @Test
    void createProductAndFindItByBrand() {
        // Given
        Product product1 = new Product("Test Product 1", "Test Brand", 10.0, "Test Category");
        Product product2 = new Product("Test Product 2", "Test Brand", 20.0, "Test Category");
        productRepository.save(product1);
        productRepository.save(product2);

        // When
        List<Product> foundProducts = productRepository.findByBrand("Test Brand");
        // Sort the list by name to ensure consistent order of products
        foundProducts.sort(Comparator.comparing(Product::getName));

        // Then
        assertEquals(2, foundProducts.size());
        assertEquals("Test Product 1", foundProducts.get(0).getName());
        assertEquals("Test Brand", foundProducts.get(0).getBrand());
        assertEquals(10.0, foundProducts.get(0).getPrice());
        assertEquals("Test Category", foundProducts.get(0).getCategory());
        assertEquals("Test Product 2", foundProducts.get(1).getName());
        assertEquals("Test Brand", foundProducts.get(1).getBrand());
        assertEquals(20.0, foundProducts.get(1).getPrice());
        assertEquals("Test Category", foundProducts.get(1).getCategory());

    }

    @Test
    void findByCategory() {
    }
}
