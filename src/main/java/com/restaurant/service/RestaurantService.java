package com.restaurant.service;

import com.restaurant.model.Restaurant;
import com.restaurant.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant save(Restaurant restaurant) {
        return restaurantRepository.add(restaurant);
    }

    public Set<Restaurant> getRestaurants() {
        return restaurantRepository.getAllRestaurants();
    }

    public Restaurant editRestaurantAddressByName(String restaurantName, String newAddress) {
        /*System.out.print("Type restaurant name: ");
        final var restaurantName = restaurantInputValidator.readLine();
        System.out.print("Type new restaurant address: ");
        final var newAddress = restaurantInputValidator.readLine();*/
        return restaurantRepository.updateRestaurantAddressByName(restaurantName, newAddress);
    }

    public void delete(String name) {
        restaurantRepository.deleteByName(name);
    }


}