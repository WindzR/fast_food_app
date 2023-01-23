package ru.job4j.dto;

import lombok.Data;
import ru.job4j.model.OrderStatus;

@Data
public class OrderStatusDTO {

    private int id;

    private String status;

    public static OrderStatusDTO of(int id, String status) {
        OrderStatusDTO statusDTO = new OrderStatusDTO();
        statusDTO.setId(id);
        statusDTO.setStatus(status);
        return statusDTO;
    }

    public static OrderStatusDTO fromOrderStatus(OrderStatus orderStatus) {
        OrderStatusDTO statusDTO = new OrderStatusDTO();
        String status = convert(orderStatus);
        statusDTO.setStatus(status);
        return statusDTO;
    }

    private static String convert(OrderStatus orderStatus) {
        return switch (orderStatus) {
            case CREATED -> "CREATED";
            case READY_FOR_DELIVERY -> "READY FOR DELIVERY";
            case DELIVERING -> "DELIVERING";
            case DELIVERED -> "DELIVERED";
            case CANCELED -> "CANCELED";
        };
    }
}
