package ru.job4j.model;

import lombok.Data;

@Data
public class Status {

    private int id;

    private String phase;

    public static Status of(String phase) {
        Status status = new Status();
        status.phase = phase;
        return status;
    }
}
