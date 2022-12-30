package ru.job4j.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Order {

    private int id;

    private String name;

    private Date created;

    private List<Product> orderList = new ArrayList<>();

    private Status status;

    public static Order of(String name) {
        Order order = new Order();
        order.name = name;
        return order;
    }

    public void addDish(Product product) {
        orderList.add(product);
    }
}
