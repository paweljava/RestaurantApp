package com.restaurant.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDTO {

    private String name;
    private String address;
    private String type;
    private String meal_name;
    private String price;

    public RestaurantDTO(String meal_name, String price) {
        this.meal_name = meal_name;
        this.price = price;
    }
}