package model;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Order {

    private int id;

    private String name;

    private Date created;

    private List<Dish> orderList = new ArrayList<>();

    public static Order of(String name) {
        Order order = new Order();
        order.name = name;
        return order;
    }

    public void addDish(Dish dish) {
        orderList.add(dish);
    }
}
