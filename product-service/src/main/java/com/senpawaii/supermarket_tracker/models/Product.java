package com.senpawaii.supermarket_tracker.models;

import org.springframework.data.annotation.Id;

public class Product {

    @Id
    public String id;

    public String name;
    public String brand;
    public Double price;
    public String category;

    public Product() {}

    public Product(String name, String brand, Double price, String category) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return String.format(
                "Product[id=%s, name='%s', brand='%s', price='%f', category='%s']",
                id, name, brand, price, category);
    }
}
