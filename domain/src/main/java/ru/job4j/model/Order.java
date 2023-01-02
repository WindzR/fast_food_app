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

    private OrderStatus status;

    public static Order of(String name) {
        Order order = new Order();
        order.name = name;
        order.created = new Date();
        order.status = OrderStatus.CREATED;
        return order;
    }

    public void addProduct(Product product) {
        orderList.add(product);
    }
}
