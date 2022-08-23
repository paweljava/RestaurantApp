package com.restaurant.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMealDto {

    private UUID id;
    private UUID restaurantId;
    private String name;
    private float price;

    public CreateMealDto(UUID restaurantId, String name, float price) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.price = price;
    }
}

