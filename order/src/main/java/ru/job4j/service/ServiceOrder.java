package ru.job4j.service;

import ru.job4j.dto.OrderDTO;
import ru.job4j.model.Order;
import ru.job4j.model.OrderStatus;

import java.util.Optional;

public interface ServiceOrder {

    Optional<OrderDTO> createOrder(Order order);

    Optional<OrderDTO> changeOrder(int id, Order order);

    Optional<OrderDTO> cancelOrder(int id);

    Optional<OrderStatus> getStatus(int id);
}
