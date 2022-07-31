package com.restaurant.service;

import com.restaurant.model.Restaurant;
import com.restaurant.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.getAllRestaurants();
    }

    public Restaurant getRestaurantByName(String name) {
        return restaurantRepository.getRestaurantByName(name);
    }

    public Restaurant getRestaurantByAddress(String address) {
        return restaurantRepository.getRestaurantByAddress(address);
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