package com.restaurant.model;

import java.util.List;
import java.util.stream.Collectors;

public class RestaurantDtoMapper {

    public static List<RestaurantDto> mapToRestaurantDtos(List<Restaurant> restaurants) {
        return restaurants.stream()
                .map(RestaurantDtoMapper::mapToRestaurantDto)
                .collect(Collectors.toList());
    }

    private static RestaurantDto mapToRestaurantDto(Restaurant restaurant) {
        return new RestaurantDto(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getAddress(),
                restaurant.getType());
    }

    // nie dziala metoda builder
    /*@Builder(builderMethodName = "_builder")
    private RestaurantDto mapToRestaurantDtoTest(Restaurant restaurant) {
        return new RestaurantDto().bulider
                .id(restaurant.getId())
                .name(restaurant.getName())
                .address(restaurant.getAddress())
                .type(restaurant.getType())
                .build();
    }*/
}
