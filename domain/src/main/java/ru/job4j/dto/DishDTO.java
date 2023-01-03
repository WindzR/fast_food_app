package ru.job4j.dto;

import lombok.Data;
import ru.job4j.model.Dish;

@Data
public class DishDTO {

    private int id;

    private String name;

    private long cookingTime;

    public static DishDTO fromDish(Dish dish) {
        DishDTO dishDTO = new DishDTO();
        dishDTO.setName(dish.getName());
        dishDTO.setCookingTime(dish.getCookingTime());
        return dishDTO;
    }
}
