package ru.job4j.service;

import org.springframework.stereotype.Service;
import ru.job4j.dao.DishRepository;
import ru.job4j.model.Dish;

import java.util.List;
import java.util.Optional;

@Service
public class DishServiceImpl implements DishService {

    private final DishRepository dishDAO;

    public DishServiceImpl(final DishRepository dishDAO) {
        this.dishDAO = dishDAO;
    }

    @Override
    public Dish addNewDish(Dish dish) {
        dish.setAvailable(true);
        dishDAO.save(dish);
        return dish;
    }

    @Override
    public boolean removeDishById(int id) {
        Optional<Dish> removedDish = dishDAO.findById(id);
        removedDish.ifPresent(dishDAO::delete);
        return removedDish.isPresent();
    }

    @Override
    public Optional<Dish> changeDish(int id, Dish dish) {
        Optional<Dish> oldDish = dishDAO.findById(id);
        if (oldDish.isPresent()) {
            Dish updateDish = oldDish.get();
            updateDish.setName(dish.getName());
            updateDish.setCookingTime(dish.getCookingTime());
            updateDish.setAvailable(dish.isAvailable());
            dishDAO.save(updateDish);
            return Optional.of(updateDish);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Dish> setUnavailableDish(int id) {
        Optional<Dish> dish = dishDAO.findById(id);
        if (dish.isPresent()) {
            Dish updateDish = dish.get();
            updateDish.setAvailable(false);
            dishDAO.save(updateDish);
            return Optional.of(updateDish);
        }
        return Optional.empty();
    }

    @Override
    public List<Dish> getAvailableDishes() {
        return dishDAO.findDishByAvailable();
    }

    @Override
    public List<Dish> getAllDishes() {
        return dishDAO.findAll();
    }

    @Override
    public Optional<Dish> getDishById(int id) {
        return dishDAO.findById(id);
    }
}
