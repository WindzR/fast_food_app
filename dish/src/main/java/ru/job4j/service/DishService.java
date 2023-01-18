package ru.job4j.service;

import ru.job4j.model.Dish;

import java.util.List;
import java.util.Optional;

public interface DishService {

    Dish addNewDish(Dish dish);

    boolean removeDishById(int id);

    Optional<Dish> changeDish(int id, Dish dish);

    Optional<Dish> setUnavailableDish(int id);

    List<Dish> getAvailableDishes();

    List<Dish> getAllDishes();

    Optional<Dish> getDishById(int id);
}
