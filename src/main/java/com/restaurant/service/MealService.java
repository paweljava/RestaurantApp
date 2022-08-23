package com.restaurant.service;

import com.restaurant.model.Meal;
import com.restaurant.model.MealDto;
import com.restaurant.repository.MealRepository;
import com.restaurant.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MealService {

    private MealRepository mealRepository;
    private RestaurantRepository restaurantRepository;

    @Autowired
    public MealService(MealRepository mealRepository, RestaurantRepository restaurantRepository) {
        this.mealRepository = mealRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public Meal addMealToRestaurant(UUID id, MealDto mealDto) {
        restaurantRepository.findById(id).orElseThrow();
        final var newMeal = new Meal(id, mealDto.getName(), mealDto.getPrice());
        return mealRepository.save(newMeal);
    }
}
