package ru.job4j.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.model.Order;

import java.util.Optional;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {

    Optional<Order> findOrderById(int id);
}
