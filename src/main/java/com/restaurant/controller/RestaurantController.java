package com.restaurant.controller;

import com.restaurant.model.Restaurant;
import com.restaurant.repository.RestaurantRepository;
import com.restaurant.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantController(RestaurantService restaurantService, RestaurantRepository restaurantRepository) {
        this.restaurantService = restaurantService;
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping
    public Set<Restaurant> getRestaurants() {
        return restaurantService.getRestaurants();
    }

    @PostMapping
    public Restaurant addRestaurant(@RequestBody Restaurant restaurant) {
        return restaurantService.save(restaurant);
    }

    @PatchMapping
    public Restaurant updateRestaurantAddressByName(@RequestParam(name = "name") String name, @RequestBody String address) {
        return restaurantService.editRestaurantAddressByName(name, address);
    }

    @DeleteMapping()
    public void delete(@RequestParam String name) {
        restaurantService.delete(name);
    }
}
