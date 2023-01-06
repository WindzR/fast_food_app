package ru.job4j.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import ru.job4j.dto.DishDTO;

public class DishService {

    @Value("${api-url}")
    private String url;

    private final RestTemplate client;

    public DishService(final RestTemplate dishClient) {
        this.client = dishClient;
    }

    public DishDTO addNewDish(DishDTO dish) {
        System.out.println(url);
        return client.postForEntity(
                url, dish, DishDTO.class
        ).getBody();
    }

    public boolean removeDish(String id) {
        return client.exchange(
                String.format("%s?id=%s", url, id),
                HttpMethod.DELETE,
                HttpEntity.EMPTY,
                Void.class
        ).getStatusCode() != HttpStatus.NOT_FOUND;
    }
}
