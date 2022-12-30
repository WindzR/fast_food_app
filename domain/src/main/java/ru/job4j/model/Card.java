package ru.job4j.model;

import lombok.Data;

@Data
public class Card {

    private int id;

    private int bonuses;

    private int discount;

    public static Card of(int bonuses, int discount) {
        Card card = new Card();
        card.bonuses = bonuses;
        card.discount = discount;
        return card;
    }
}
