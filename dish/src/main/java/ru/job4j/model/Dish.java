package ru.job4j.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "dishes")
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    @EqualsAndHashCode.Include
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
