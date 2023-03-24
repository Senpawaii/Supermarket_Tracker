package com.supermarket_tracker.product;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String>{
    Product findByName(String name);
    List<Product> findByBrand(String brand);
    List<Product> findByCategory(String category);
}
