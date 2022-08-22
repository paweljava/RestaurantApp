package com.restaurant.model;

import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.net.Proxy;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDto {

    private UUID id;
    private String name;
    private String address;
    @Enumerated(EnumType.STRING)
    private RestaurantType type;

    /*public RestaurantDto(String name, String address, RestaurantType type) {
        this.name = name;
        this.address = address;
        this.type = type;
    }*/
}