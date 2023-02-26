package com.senpawaii.supermarket_tracker;

import com.senpawaii.supermarket_tracker.models.Product;
import com.senpawaii.supermarket_tracker.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

// The SpringBootApplication is a convenience annotation that adds:
//      @configuration, @EnableAutoConfiguration and @ComponentScan
@SpringBootApplication
@EnableMongoRepositories("com.senpawaii.supermarket_tracker")
public class ProductServiceApplication implements CommandLineRunner {
    @Autowired
    private ProductRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        repository.deleteAll();

        // Save some Products
        repository.save(new Product("Queijo", "Limiano", 1.24));
        repository.save(new Product("Água", "Serra da Estrela", 1.50));

        // Fetch all Products
        System.out.println("Products found with findAll():");
        System.out.println("------------------------------------");
        for(Product product : repository.findAll()) {
            System.out.println(product);
        }

        // Fetch an individual product
        System.out.println("Product found with findByName('Queijo'):");
        System.out.println("------------------------------------");
        System.out.println(repository.findByName("Queijo"));

        System.out.println("Products found with findByBrand('Serra da Estrela'):");
        System.out.println("------------------------------------");
        for(Product product : repository.findByBrand("Serra da Estrela")) {
            System.out.println(product);
        }
    }
}
