package ru.job4j.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import ru.job4j.dto.DishDTO;

import java.util.List;

public class DishService {

    @Value("${api-url}")
    private String URL;

    private final RestTemplate client;

    public DishService(final RestTemplate dishClient) {
        this.client = dishClient;
    }

    public DishDTO addNewDish(DishDTO dish) {
        String url = URL + "/new-dish";
        System.out.println(url);
        return client.postForEntity(
                url, dish, DishDTO.class
        ).getBody();
    }

    public boolean removeDish(String id) {
        String url = URL + "/" + id;
        System.out.println(url);
        return client.exchange(
                url,
                HttpMethod.DELETE,
                HttpEntity.EMPTY,
                Void.class
        ).getStatusCode() != HttpStatus.NOT_FOUND;
    }

    public boolean changeDish(String id, DishDTO dish) {
        String url = URL + "/" + id;
        System.out.println(url);
        return client.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(dish),
                DishDTO.class
        ).getStatusCode() != HttpStatus.NOT_FOUND;
    }

    public boolean setUnavailableDish(String id) {
        String url = URL + "unavailable/" + id;
        System.out.println(url);
        return client.exchange(
                url,
                HttpMethod.PATCH,
                HttpEntity.EMPTY,
                DishDTO.class
        ).getStatusCode() != HttpStatus.NOT_FOUND;
    }

    public List<DishDTO> getAvailableDishes() {
        String url = URL + "/get-available";
        System.out.println(url);
        return client.exchange(
                url,
                HttpMethod.GET, null,
                new ParameterizedTypeReference<List<DishDTO>>() { }
        ).getBody();
    }

    public List<DishDTO> getAllDishes() {
        String url = URL + "/all";
        System.out.println(url);
        return client.exchange(
                url,
                HttpMethod.GET, null,
                new ParameterizedTypeReference<List<DishDTO>>() { }
        ).getBody();
    }

    public DishDTO getDishById(String id) {
        String url = URL + "/" + id;
        System.out.println(url);
        return client.getForEntity(
                url,
                DishDTO.class
        ).getBody();
    }
}
