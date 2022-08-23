package com.restaurant.controller;

import com.restaurant.model.Meal;
import com.restaurant.model.MealDto;
import com.restaurant.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/restaurants/meals")
public class MealController {

    private MealService mealService;

    @Autowired
    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    @PutMapping("/meals")
    public Meal addMealToRestaurant(@RequestParam UUID id, @RequestBody MealDto mealDto) {
        return mealService.addMealToRestaurant(id, mealDto);
    }
}
