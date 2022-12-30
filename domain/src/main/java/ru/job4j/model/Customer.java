package ru.job4j.model;

import lombok.Data;

@Data
public class Customer {

    private int id;

    private String firstName;

    private  String secondName;

    private String phone;

    private String sex;

    private String mail;

    private Card card;

    public static Customer of(String firstName, String secondName,
                              String phone, String sex,
                              String mail, Card card) {
        Customer customer = new Customer();
        customer.firstName = firstName;
        customer.secondName = secondName;
        customer.phone = phone;
        customer.sex = sex;
        customer.mail = mail;
        customer.card = card;
        return customer;
    }
}
