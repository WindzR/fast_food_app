package service;

import model.Dish;

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
