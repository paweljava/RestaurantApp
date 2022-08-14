package com.restaurant.service;

import com.restaurant.model.Meal;
import com.restaurant.model.MealDTO;
import com.restaurant.model.Restaurant;
import com.restaurant.model.RestaurantDTO;
import com.restaurant.repository.MealRepository;
import com.restaurant.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final MealRepository mealRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository, MealRepository mealRepository) {
        this.restaurantRepository = restaurantRepository;
        this.mealRepository = mealRepository;
    }

    public Restaurant save(RestaurantDTO restaurantDTO) {
        final var restaurant = new Restaurant(
                restaurantDTO.getName(),
                restaurantDTO.getAddress(),
                restaurantDTO.getType()
        );
        return restaurantRepository.save(restaurant);
    }

    public Meal saveMeal(MealDTO mealDTO) {
        final var meal = new Meal(mealDTO.getName(), mealDTO.getPrice());
        return mealRepository.save(meal);
    }

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public Restaurant getRestaurantByName(String name) {
        return restaurantRepository.findByName(name);
    }

    public Restaurant getRestaurantById(UUID id) {
        return restaurantRepository.findAllById(id);
    }
    public Restaurant getRestaurantByAddress(String address) {
        return restaurantRepository.getRestaurantByAddress(address);
    }
/*
    public Restaurant editRestaurantAddressByName(Restaurant restaurant) {
        return restaurantRepository.updateRestaurantAddressByName(restaurant);
    }

    public Restaurant editRestaurantAddressById(Restaurant restaurant) {
        return restaurantRepository.updateRestaurantAddressById(restaurant);
    }

    public void delete(String name) {
        restaurantRepository.deleteByName(name);
    }

*/
    /*public Restaurant addMealByRestaurantName(Restaurant restaurant) {
        return restaurantRepository.addMealByRestaurantName();
    }*/
}