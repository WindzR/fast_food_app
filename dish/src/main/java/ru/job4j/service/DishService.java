package ru.job4j.service;

import ru.job4j.model.Dish;

import java.util.List;

public interface DishService {

    void addNewDish(Dish dish);

    void removeDishById(int id);

    Dish changeDish(Dish dish);

    Dish setUnavailableDish(int id);

    List<Dish> getAvailableDishes();

    List<Dish> getAllDishes();

    Dish getDishById(int id);
}
