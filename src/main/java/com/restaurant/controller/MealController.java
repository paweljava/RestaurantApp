package com.restaurant.controller;

import com.restaurant.model.Meal;
import com.restaurant.model.CreateMealDto;
import com.restaurant.model.MealDto;
import com.restaurant.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/restaurants")
public class MealController {

    private MealService mealService;

    @Autowired
    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    @PutMapping("/meals")
    public MealDto addMealToRestaurantDto(@RequestParam UUID id, @RequestBody CreateMealDto createMealDto) {
        return mealService.addMealToRestaurant(id, createMealDto);
    }
}
