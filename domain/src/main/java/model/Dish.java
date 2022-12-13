package model;

import lombok.Data;

/**
 * cookingTime - время готовки в секундах
 */
@Data
public class Dish {

    private int id;

    private String name;

    private long cookingTime;

    private boolean available;

    public static Dish of(String name, long cookingTime) {
        Dish dish = new Dish();
        dish.name = name;
        dish.cookingTime = cookingTime;
        dish.available = true;
        return dish;
    }

}
