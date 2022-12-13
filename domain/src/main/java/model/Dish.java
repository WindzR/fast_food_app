package model;

import lombok.Data;

@Data
public class Dish {

    private int id;

    private String name;

    private boolean available;

    public static Dish of(String name) {
        Dish dish = new Dish();
        dish.name = name;
        dish.available = true;
        return dish;
    }

}
