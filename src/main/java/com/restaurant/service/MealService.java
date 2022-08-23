package com.restaurant.service;

import com.restaurant.model.Meal;
import com.restaurant.model.CreateMealDto;
import com.restaurant.model.MealDto;
import com.restaurant.model.MealDtoMapper;
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

    public MealDto addMealToRestaurant(UUID idRestaurant, CreateMealDto createMealDto) {
        restaurantRepository.findById(idRestaurant).orElseThrow();
        var newMeal = new Meal(idRestaurant, createMealDto.getName(), createMealDto.getPrice());
        var savedMeal = mealRepository.save(newMeal);
        return MealDtoMapper.mapToMealDto(savedMeal);
    }
}
