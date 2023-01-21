package ru.job4j.service;

import ru.job4j.dto.DishDTO;

import java.util.List;
import java.util.Optional;

public interface RepoService {

    DishDTO addEntity(DishDTO dish);

    boolean removeEntity(int id);

    boolean changeEntity(int id, DishDTO dish);

    boolean setUnavailableEntity(int id);

    List<DishDTO> getAvailableEntities();

    List<DishDTO> getAllEntities();

    Optional<DishDTO> getEntityById(int id);
}
