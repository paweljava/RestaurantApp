package com.restaurant.service;

import com.restaurant.model.Meal;
import com.restaurant.model.Restaurant;
import com.restaurant.model.RestaurantDto;
import com.restaurant.repository.MealRepository;
import com.restaurant.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    public static final int PageSIZE = 5;
    private final RestaurantRepository restaurantRepository;
    private final MealRepository mealRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository, MealRepository mealRepository) {
        this.restaurantRepository = restaurantRepository;
        this.mealRepository = mealRepository;
    }

    /*public Restaurant save(RestaurantDto restaurantDTO) {
        final var restaurant = new Restaurant(
                restaurantDTO.getName(),
                restaurantDTO.getAddress(),
                restaurantDTO.getType()
        );
        return restaurantRepository.save(restaurant);
    }

    public Meal saveMeal(MealDto mealDTO) {
        final var meal = new Meal(mealDTO.getName(), mealDTO.getPrice());
        return mealRepository.save(meal);
    }*/

    public List<Restaurant> getRestaurants(int page, Sort.Direction sort) {
        return restaurantRepository.findAllRestaurant(PageRequest.of(page, PageSIZE, Sort.by(sort, "name")));
    }

    /*public Restaurant getRestaurantByName(String name) {
        return restaurantRepository.findByName(name);
    }*/

    public Restaurant getRestaurantById(UUID id) {
        return restaurantRepository.findById(id)
                .orElseThrow();
    }

    public List<Restaurant> getRestaurantsWithMeals(int page, Sort.Direction sort) {
        var allRestaurants = restaurantRepository.findAllRestaurant(PageRequest.of(page, PageSIZE,
                Sort.by(sort, "name")));
        List<UUID> uuids = allRestaurants.stream()
                .map(restaurant -> restaurant.getId())
                .collect(Collectors.toList());
        var meals = mealRepository.findAllByRestaurantIdIn(uuids);
        allRestaurants.forEach(restaurant -> restaurant.setMeals(extractMeals(meals, restaurant.getId())));
        return allRestaurants;
    }

    private List<Meal> extractMeals(List<Meal> meals, UUID Id) {
        return meals.stream()
                .filter(meal -> meal.getRestaurantId().equals(Id))
                .collect(Collectors.toList());
    }

    public Restaurant addRestaurant(RestaurantDto restaurantDto) {
        final var restaurant = new Restaurant(
                restaurantDto.getName(),
                restaurantDto.getAddress(),
                restaurantDto.getType()
        );
        return restaurantRepository.save(restaurant);
    }

    //@Transactional
    public Restaurant editRestaurantById(Restaurant restaurant) {
        var restaurantEdited = restaurantRepository.findById(restaurant.getId()).orElseThrow();
        restaurantEdited.setName(restaurant.getName());
        restaurantEdited.setAddress(restaurant.getAddress());
        restaurantEdited.setType(restaurant.getType());
        return restaurantRepository.save(restaurantEdited);
    }

    public void deleteRestaurant(UUID id) {
        restaurantRepository.deleteById(id);
    }

   /* public Restaurant getRestaurantByAddress(String address) {
        return restaurantRepository.getRestaurantByAddress(address);
    }*/

    /*public Restaurant editRestaurantAddressByNamePatch(String name, Restaurant restaurant) {
        final var restaurantEdited = restaurantRepository.findByName(name);
        restaurantEdited.setAddress(restaurant.getAddress());
        return restaurantRepository.save(restaurantEdited);
    }

    public Restaurant editRestaurantAddressByNamePut(String name, Restaurant restaurant) {
        var restaurantEdited = restaurantRepository.findByName(name);
        restaurantEdited.setName(restaurant.getName());
        restaurantEdited.setAddress(restaurant.getAddress());
        restaurantEdited.setType(restaurant.getType());
        return restaurantRepository.save(restaurantEdited);
    }*/

    /*public Restaurant editRestaurantAddressById(Restaurant restaurant) {
        return restaurantRepository.updateRestaurantAddressById(restaurant);
    }*/

    /*public void delete(String name) {
        restaurantRepository.deleteByName(name);
    }*/


    /*public Restaurant addMealByRestaurantName(Restaurant restaurant) {
        return restaurantRepository.addMealByRestaurantName();
    }*/
}