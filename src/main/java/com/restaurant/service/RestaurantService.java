package com.restaurant.service;

import com.restaurant.model.Restaurant;
import com.restaurant.model.RestaurantDTO;
import com.restaurant.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant save(RestaurantDTO restaurantDTO) {
        final var restaurant = new Restaurant(
                restaurantDTO.getName(),
                restaurantDTO.getAddress(),
                restaurantDTO.getType()
        );
        return restaurantRepository.add(restaurant);
    }

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.getAllRestaurants();
    }

    public List<Restaurant> getAllRestaurantsAndMeals() {

        return restaurantRepository.getAllRestaurantsAndMeals();
    }

    public Restaurant getRestaurantByName(String name) {
        return restaurantRepository.getRestaurantByName(name);
    }

    public Restaurant getRestaurantByAddress(String address) {
        return restaurantRepository.getRestaurantByAddress(address);
    }

    public Restaurant editRestaurantAddressByName(Restaurant restaurant) {
        return restaurantRepository.updateRestaurantAddressByName(restaurant);
    }

    public Restaurant editRestaurantAddressById(Restaurant restaurant) {
        return restaurantRepository.updateRestaurantAddressById(restaurant);
    }

    public void delete(String name) {
        restaurantRepository.deleteByName(name);
    }

    public Restaurant getRestaurantById(UUID id) {
        return restaurantRepository.getRestaurantById(id);
    }

    /*public Restaurant addMealByRestaurantName(Restaurant restaurant) {
        return restaurantRepository.addMealByRestaurantName();
    }*/
}