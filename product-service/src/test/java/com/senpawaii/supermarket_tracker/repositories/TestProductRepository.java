package com.senpawaii.supermarket_tracker.repositories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import com.senpawaii.supermarket_tracker.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
public class TestProductRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void bootstrapTestDataWithMongoTemplate() {
        Product product = new Product("testName", "testBrand", 1.5, "testCategory");
        mongoTemplate.insert(product);

        assertEquals("testName", product.getName());
        assertEquals("testBrand", product.getBrand());
        assertEquals(1.5, product.getPrice());
        assertEquals("testCategory", product.getCategory());
    }

    // Add new Tests here

    @AfterEach
    void cleanUpDatabase() {
        mongoTemplate.getDb().drop();
    }
}
