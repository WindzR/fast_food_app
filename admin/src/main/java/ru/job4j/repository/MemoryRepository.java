package ru.job4j.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.dto.DishDTO;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class MemoryRepository {

    private final Map<Integer, DishDTO> map = new HashMap<>();
    private final AtomicInteger count = new AtomicInteger(1);

    public MemoryRepository() {
        DishDTO dto1 = DishDTO.of(1, "King Burger", 300);
        DishDTO dto2 = DishDTO.of(2, "Salad Ceasar", 240);
        DishDTO dto3 = DishDTO.of(3, "Pepsi-cola", 30);
        map.put(count.getAndIncrement(), dto1);
        map.put(count.getAndIncrement(), dto2);
        map.put(count.getAndIncrement(), dto3);
    }

    public void saveDish(DishDTO dto) {
        DishDTO dish = DishDTO.of(count.get(),
                dto.getName(),
                dto.getCookingTime());
        map.put(count.getAndIncrement(), dish);
    }

    public Collection<DishDTO> getDishes() {
        Collection<DishDTO> values = map.values();
        System.out.println(values);
        return map.values();
    }

    public Optional<DishDTO> getDishById(int id) {
        Collection<DishDTO> dishes = getDishes();
        return dishes.stream()
                .filter(dish -> dish.getId() == id)
                .findFirst();
    }

    public boolean updateDish(DishDTO dish) {
        boolean isUpdated = false;
        Integer key = 0;
        for (Integer curKey : map.keySet()) {
            var currentValue = map.get(curKey);
            if (currentValue.getId() == dish.getId()) {
                key = curKey;
                isUpdated = true;
                break;
            }
        }
        System.out.println("Key = " + key);
        System.out.println("Dish = " + dish);
        map.replace(key, dish);
        return isUpdated;
    }

    public boolean deleteById(int id) {
        boolean isDeleted = false;
        Integer key = 0;
        for (Integer curKey : map.keySet()) {
            var currentValue = map.get(curKey);
            if (currentValue.getId() == id) {
                key = curKey;
                isDeleted = true;
                map.remove(key);
                break;
            }
        }
        System.out.println("Key = " + key);
        return isDeleted;
    }
}
