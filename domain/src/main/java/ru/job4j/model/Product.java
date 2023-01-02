package ru.job4j.model;

import lombok.Data;

@Data
public class Product {

    private int id;

    private String name;

    private int price;

    private boolean available;

    public static Product of(String name, int price, boolean available) {
        Product product = new Product();
        product.name = name;
        product.price = price;
        product.available = available;
        return product;
    }
}
