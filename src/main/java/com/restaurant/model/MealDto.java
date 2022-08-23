package com.restaurant.model;

import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class MealDto {

    private UUID id;
    private UUID restaurantId;
    private String name;
    private float price;
}
