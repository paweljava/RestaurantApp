package com.restaurant.model;

import java.util.List;
import java.util.stream.Collectors;

public class MealDtoMapper{


    public static List<MealDto> mapToMealDtos(List<Meal> meals) {
        return meals.stream()
                .map(meal -> mapToMealDto(meal))
                .collect(Collectors.toList());
    }

    public static MealDto mapToMealDto(Meal meal) {
        return new MealDto(
                meal.getId(),
                meal.getRestaurantId(),
                meal.getName(),
                meal.getPrice());
    }
}
