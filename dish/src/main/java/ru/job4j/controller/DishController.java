package ru.job4j.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMapAdapter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.dto.DishDTO;
import ru.job4j.model.Dish;
import ru.job4j.service.DishServiceImpl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/dishes")
public class DishController {

    private static final Logger LOGGER = LoggerFactory.getLogger(
            DishController.class.getSimpleName()
    );

    private final DishServiceImpl dishService;

    public DishController(final DishServiceImpl dishService) {
        this.dishService = dishService;
    }

    /**
     * Добавляет в сервис новое блюдо
     * @param dish параметры блюда
     * @return блюдо, зарегистрированное в сервисе
     */
    @PostMapping("/new-dish")
    public ResponseEntity<DishDTO> addDish(@RequestBody Dish dish) {
        dishService.addNewDish(dish);
        DishDTO dto = DishDTO.fromDish(dish);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    /**
     * Удаление блюда по его id
     * @param id блюда в сервисе
     * @return ответ сервера об успешном удалении / невозможности найти по id блюда
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeDish(@PathVariable int id) {
        boolean status = dishService.removeDishById(id);
        return ResponseEntity
                .status(status ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                .build();
    }

    /**
     * Изменение параметров уже зарегестрированного блюда
     * @param id блюда в сервисе
     * @param dish параметры измененного блюда
     * @return ответ сервиса об успешном сохранении новых параметров или отсутствия блюда с таким id
     */
    @PutMapping("/{id}")
    public ResponseEntity<DishDTO> changeDish(@PathVariable int id, @RequestBody Dish dish) {
        Optional<Dish> updateDish = dishService.changeDish(id, dish);
        DishDTO dto = DishDTO.fromDish(updateDish.get());
        Optional<DishDTO> dishDTO = Optional.of(dto);
        return new ResponseEntity<DishDTO>(
                dishDTO.orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Dish is not found. Please, check require id.")
                ),
                HttpStatus.OK
        );
    }

    /**
     * Изменить флаг доступности блюда
     * @param id редактируемого блюда
     * @return ответ об успешности/неуспешности запроса
     */
    @PatchMapping("/unavailable/{id}")
    public ResponseEntity<DishDTO> setUnavailableDish(@PathVariable int id) {
        var dishRepository = dishService.setUnavailableDish(id);
        if (dishRepository.isPresent()) {
            DishDTO dto = DishDTO.fromDish(dishRepository.get());
            return new ResponseEntity<DishDTO>(
                    dto, HttpStatus.ACCEPTED
            );
        }
        return new ResponseEntity<DishDTO>(
                new DishDTO(),
                new MultiValueMapAdapter<String, String>(
                        Map.of("NOT FOUND", List.of(
                                "Dish is not found. Please, check require id.")
                        )),
                HttpStatus.NOT_FOUND
        );
    }

    /**
     * Получить список доступных блюд
     * @return список блюд
     */
    @GetMapping("/get-available")
    public List<DishDTO> getAvailableDishes() {
        List<Dish> availableDishes = dishService.getAvailableDishes();
        return mapToDishDTO(availableDishes);
    }

    /**
     * Получить список всех, зарегестрированных в сервисе блюд
     * @return список блюд
     */
    @GetMapping("/all")
    public List<DishDTO> getAllDishes() {
        List<Dish> allDishes = dishService.getAllDishes();
        return mapToDishDTO(allDishes);
    }

    /**
     * Получить блюдо по его id
     * @param id блюда
     * @return блюдо / сообщение, что блюдо не найдено
     */
    @GetMapping("/{id}")
    public ResponseEntity<DishDTO> getDishById(@PathVariable int id) {
        var dish = dishService.getDishById(id);
        Optional<DishDTO> dto = Optional.of(DishDTO.fromDish(dish.get()));
        return new ResponseEntity<>(
                dto.orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Dish is not found. Please, check required id."
                        )
                ),
                HttpStatus.OK
        );
    }

    private List<DishDTO> mapToDishDTO(List<Dish> dishes) {
        return dishes.stream()
                .map(DishDTO::fromDish)
                .collect(Collectors.toList());
    }
}
