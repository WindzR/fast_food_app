package ru.job4j.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import ru.job4j.dto.DishDTO;

import java.util.List;
import java.util.Optional;

@Service
public class DishService {

    @Value("${api-url}")
    private String baseURL;

    private final RestTemplate client;

    public DishService(final RestTemplate dishClient) {
        this.client = dishClient;
    }

    public DishDTO addNewDish(DishDTO dish) {
        String url = baseURL + "/new-dish";
        System.out.println(url);
        return client.postForEntity(
                url, dish, DishDTO.class
        ).getBody();
    }

    public boolean removeDish(int id) {
        String url = baseURL + "/" + id;
        System.out.println(url);
        return client.exchange(
                url,
                HttpMethod.DELETE,
                HttpEntity.EMPTY,
                Void.class
        ).getStatusCode() != HttpStatus.NOT_FOUND;
    }

    public boolean changeDish(int id, DishDTO dish) {
        String url = baseURL + "/" + id;
        System.out.println(url);
        return client.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(dish),
                DishDTO.class
        ).getStatusCode() != HttpStatus.NOT_FOUND;
    }

    public boolean setUnavailableDish(String id) {
        String url = baseURL + "unavailable/" + id;
        System.out.println(url);
        return client.exchange(
                url,
                HttpMethod.PATCH,
                HttpEntity.EMPTY,
                DishDTO.class
        ).getStatusCode() != HttpStatus.NOT_FOUND;
    }

    public List<DishDTO> getAvailableDishes() {
        String url = baseURL + "/get-available";
        System.out.println(url);
        return client.exchange(
                url,
                HttpMethod.GET, null,
                new ParameterizedTypeReference<List<DishDTO>>() { }
        ).getBody();
    }

    public List<DishDTO> getAllDishes() {
        String url = baseURL + "/all";
        System.out.println(url);
        return client.exchange(
                url,
                HttpMethod.GET, null,
                new ParameterizedTypeReference<List<DishDTO>>() { }
        ).getBody();
    }

    public Optional<DishDTO> getDishById(int id) {
        String url = baseURL + "/" + id;
        System.out.println(url);
        DishDTO dish = client.getForEntity(
                url,
                DishDTO.class
        ).getBody();
        return Optional.of(dish);
    }
}
