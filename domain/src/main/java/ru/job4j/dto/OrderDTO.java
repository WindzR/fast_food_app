package ru.job4j.dto;

import lombok.Data;
import ru.job4j.model.Order;
import ru.job4j.model.OrderStatus;
import ru.job4j.model.Product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class OrderDTO {

    private int id;

    private String name;

    private Date created;

    private List<Product> orderList = new ArrayList<>();

    private OrderStatus status;

    public static OrderDTO fromOrder(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setName(order.getName());
        orderDTO.setCreated(order.getCreated());
        orderDTO.setOrderList(order.getOrderList());
        orderDTO.setStatus(order.getStatus());
        return orderDTO;
    }

    public Order toOrder() {
        Order order = new Order();
        order.setId(id);
        order.setName(name);
        order.setCreated(created);
        order.setOrderList(orderList);
        order.setStatus(status);
        return order;
    }
}
