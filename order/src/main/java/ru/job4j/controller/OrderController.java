package ru.job4j.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.dto.OrderDTO;
import ru.job4j.dto.OrderStatusDTO;
import ru.job4j.model.Order;
import ru.job4j.model.OrderStatus;
import ru.job4j.service.ServiceOrder;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(
            OrderController.class.getSimpleName()
    );

    private final ServiceOrder serviceOrder;

    public OrderController(final ServiceOrder orders) {
        this.serviceOrder = orders;
    }

    /**
     * Создание заказа
     * @param order все параметры заказа
     * @return сохранение заказа в сервисе и ответ о его статусе
     */
    @PostMapping("/create")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody Order order) {
        return new ResponseEntity<OrderDTO>(
                serviceOrder.createOrder(order).orElse(new OrderDTO()),
                HttpStatus.CREATED
        );
    }

    /**
     * Изменение состава заказа
     * @param order все параметры заказа
     * @return сохранение заказа в сервисе и ответ о его статусе
     */
    @PutMapping("/change")
    public ResponseEntity<OrderDTO> changeOrder(@RequestParam("id") String id,
                                @RequestBody Order order) {
        return new ResponseEntity<OrderDTO>(
                serviceOrder.changeOrder(Integer.parseInt(id), order)
                        .orElse(new OrderDTO()
                ),
                HttpStatus.OK
        );
    }

    /**
     * Удаление заказа
     * @param id заказа
     * @return удаленный заказ
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> cancelOrder(@PathVariable int id) {
        Optional<OrderDTO> orderDTO = serviceOrder.cancelOrder(id);
        if (orderDTO.isPresent()) {
            String message = "Order with id = " + id + " was canceled!";
            return setupMessage("CANCELED", message);
        }
        String message = "Order with id = " + id + " was not found!";
        return setupMessage("NOT FOUND", message);
    }

    /**
     * Получить статус заказа
     * @param id конкретного заказа
     * @return статус заказа
     */
    @GetMapping("/status/{id}")
    public ResponseEntity<OrderStatusDTO> getStatus(@PathVariable int id) {
        Optional<OrderStatus> status = serviceOrder.getStatus(id);
        Optional<OrderStatusDTO> dto = Optional.of(OrderStatusDTO.fromOrderStatus(status.get()));
        OrderStatusDTO defaultStatus = OrderStatusDTO.of(0, "NOT EXIST");
        return new ResponseEntity<OrderStatusDTO>(
                dto.orElse(defaultStatus),
                HttpStatus.OK
        );
    }

    private ResponseEntity<Map<String, String>> setupMessage(String key, String value) {
        Map<String, String> body = new HashMap<>() {{
            put(key, value);
        }};
        return new ResponseEntity<>(
                body,
                HttpStatus.OK
        );
    }
}
