package com.restaurant.model;

import com.restaurant.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.stream.Collectors;

public class RestaurantDtoMapper {

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantDtoMapper(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    public static List<RestaurantDto> mapToRestaurantDtos(List<Restaurant> restaurants) {
        return restaurants.stream()
                .map(restaurant -> mapToRestaurantDto(restaurant))
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
