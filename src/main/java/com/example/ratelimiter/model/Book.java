package com.example.ratelimiter.model;

import java.util.UUID;

public class Book {
    private String name;
    private UUID id;

    public Book(String name, UUID id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }
}
