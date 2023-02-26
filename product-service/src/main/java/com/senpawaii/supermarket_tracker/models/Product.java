package com.senpawaii.supermarket_tracker.models;

import org.springframework.data.annotation.Id;

public class Product {

    @Id
    public String id;

    public String name;
    public String brand;
    public Double price;

    public Product() {}

    public Product(String name, String brand, Double price) {
        this.name = name;
        this.brand = brand;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format(
                "Product[id=%s, name='%s', brand='%s', price='%f']",
                id, name, brand, price);
    }
}
