package ru.job4j.model;

import lombok.Data;

@Data
public class Product {

    private int id;

    private String name;

    private boolean available;

    public static Product of(String name, boolean available) {
        Product product = new Product();
        product.name = name;
        product.available = available;
        return product;
    }
}
