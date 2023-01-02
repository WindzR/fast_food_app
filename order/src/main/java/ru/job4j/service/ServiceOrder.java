package ru.job4j.service;

import ru.job4j.model.Order;
import ru.job4j.model.OrderStatus;

public interface ServiceOrder {

    Order createOrder(Order order);

    Order changeOrder(Order order);

    Order cancelOrder(Order order);

    OrderStatus getStatus(Order order);
}
