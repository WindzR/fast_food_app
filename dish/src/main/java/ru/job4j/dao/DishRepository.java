package ru.job4j.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.model.Dish;
//import ru.job4j.model.Dish;

import java.util.List;
import java.util.Optional;

@Repository
public interface DishRepository extends CrudRepository<Dish, Integer> {

    List<Dish> findAll();

    @Query("SELECT DISTINCT dish FROM Dish dish WHERE dish.available = true")
    List<Dish> findDishByAvailable();

    Optional<Dish> findById(Integer id);
}
