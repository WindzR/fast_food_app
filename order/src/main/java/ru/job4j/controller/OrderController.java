package ru.job4j.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.job4j.dto.OrderDTO;
import ru.job4j.dto.OrderStatusDTO;
import ru.job4j.model.Order;
import ru.job4j.service.ServiceOrder;

@RestController
@RequestMapping("/order")
public class OrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(
            OrderController.class.getSimpleName()
    );

    private final ServiceOrder orders;

    public OrderController(final ServiceOrder orders) {
        this.orders = orders;
    }

    /**
     * Создание заказа
     * @param order все параметры заказа
     * @return сохранение заказа в сервисе и ответ о его статусе
     */
    @PostMapping("/create")
    public OrderDTO createOrder(@RequestBody Order order) {
        return null;
    }

    /**
     * Изменение состава заказа
     * @param order все параметры заказа
     * @return сохранение заказа в сервисе и ответ о его статусе
     */
    @PostMapping("/change")
    public OrderDTO changeOrder(@RequestBody Order order) {
        return null;
    }

    /**
     * Удаление заказа
     * @param id заказа
     * @return удаленный заказ
     */
    @DeleteMapping("/delete/{id}")
    public OrderDTO cancelOrder(@PathVariable int id) {
        return null;
    }

    /**
     * Получить статус заказа
     * @param id конкретного заказа
     * @return статус заказа
     */
    @GetMapping("/status/{id}")
    public OrderStatusDTO getStatus(@PathVariable int id) {
        return null;
    }
}
