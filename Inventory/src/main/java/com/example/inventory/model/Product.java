package com.example.inventory.model;

import org.springframework.data.annotation.Id;

public record Product(
        @Id
        String id,
        String name,
        int quantity,
        double price) {
}
