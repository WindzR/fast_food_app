package ru.job4j.service;

import org.springframework.stereotype.Service;
import ru.job4j.controller.KafkaOrderController;
import ru.job4j.dto.OrderDTO;
import ru.job4j.model.Order;
import ru.job4j.model.OrderStatus;
import ru.job4j.repository.OrderRepository;

import java.util.Optional;

@Service
public class ServiceOrderImpl implements ServiceOrder {

    private final OrderRepository repository;

    private final KafkaOrderController kafkaController;

    public ServiceOrderImpl(final OrderRepository repository,
                            final KafkaOrderController kafkaController) {
        this.repository = repository;
        this.kafkaController = kafkaController;
    }

    @Override
    public Optional<OrderDTO> createOrder(Order order) {
        repository.save(order);
        OrderDTO dto = OrderDTO.fromOrder(order);
        //
        kafkaController.sendNotification(dto);
        return Optional.of(dto);
    }

    @Override
    public Optional<OrderDTO> changeOrder(int id, Order order) {
        Optional<Order> optionalOrder = repository.findOrderById(id);
        if (optionalOrder.isPresent()) {
            order.setId(id);
            repository.save(order);
            OrderDTO dto = OrderDTO.fromOrder(order);
            //
            kafkaController.sendNotification(dto);
            return Optional.of(dto);
        }
        return Optional.empty();
    }

    @Override
    public Optional<OrderDTO> cancelOrder(int id) {
        Optional<Order> optionalOrder = repository.findOrderById(id);
        if (optionalOrder.isPresent()) {
            Order currentOrder = optionalOrder.get();
            currentOrder.setStatus(OrderStatus.CANCELED);
            repository.save(currentOrder);
            OrderDTO dto = OrderDTO.fromOrder(currentOrder);
            //
            kafkaController.sendNotification(dto);
            return Optional.of(dto);
        }
        return Optional.empty();
    }

    @Override
    public Optional<OrderStatus> getStatus(int id) {
        Optional<Order> optionalOrder = repository.findOrderById(id);
        if (optionalOrder.isPresent()) {
            OrderStatus status = optionalOrder.get().getStatus();
            return Optional.of(status);
        }
        return Optional.empty();
    }
}
