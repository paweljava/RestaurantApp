package com.restaurant.controller;

import com.restaurant.model.Meal;
import com.restaurant.model.MealDTO;
import com.restaurant.model.Restaurant;
import com.restaurant.model.RestaurantDTO;
import com.restaurant.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
    public List<Restaurant> getRestaurants() {
        return restaurantService.getAllRestaurants();
    }

   @GetMapping("/id/{id}")
    public Restaurant getRestaurantById(@PathVariable("id") UUID id) {
        return restaurantService.getRestaurantById(id);
    }

    @GetMapping("/{name}")
    public Restaurant getRestaurantByName(@PathVariable("name") String name) {
        return restaurantService.getRestaurantByName(name);
    }

    /*@GetMapping
    public Restaurant getRestaurantByName2(@RequestParam(name = "name") String name) {
        return restaurantService.getRestaurantByName(name);
    }

    @GetMapping("/{address}")
    public Restaurant getRestaurantByAddress(@PathVariable("address") String address) {
        return restaurantService.getRestaurantByAddress(address);
    }

    @GetMapping
    public Restaurant getRestaurantByAddress2(@RequestParam(address = "address") String address) {
        return restaurantService.getRestaurantByAddress(address);
    }*/

    @PostMapping
    public Restaurant addRestaurant(@RequestBody RestaurantDTO restaurantDto) {
        return restaurantService.save(restaurantDto);
    }

    @PostMapping("/{name}")
    public Meal addMeal(@PathVariable ("name") @RequestBody MealDTO mealDTO) {
        return restaurantService.saveMeal(mealDTO);
    }

    /*@PatchMapping("/{name}")
    public Restaurant updateRestaurantAddressByName(@PathVariable("name") String name, @RequestBody Restaurant updatedRestaurant) {
        Restaurant restaurant = restaurantService.getRestaurantByName(name);
        restaurant.setAddress(updatedRestaurant.getAddress());
        return restaurantService.editRestaurantAddressByName(restaurant);
    }*/

    /*@PatchMapping("/{name}")
    public Restaurant addMealByRestaurantName(@PathVariable("name") String name, @RequestBody Meal meal) {
        Restaurant restaurant = restaurantService.getRestaurantByName(name);
        restaurant.getMealList().add(meal);
        return restaurantService.addMealByRestaurantName(restaurant);
    }*/

    /*@PatchMapping("/id/{id}")
    public Restaurant updateRestaurantAddressById(@PathVariable("id") UUID id, @RequestBody Restaurant updateRestaurant) {
        Restaurant restaurant = restaurantService.getRestaurantById(id);
        restaurant.setAddress(updateRestaurant.getAddress());
        return restaurantService.editRestaurantAddressById(restaurant);
    }*/

    /*@PatchMapping
    public Restaurant updateRestaurantAddressByName(@RequestParam(name = "name") String name, @RequestBody String address) {
        return restaurantService.editRestaurantAddressByName(name, address);
    }*/

    /*@DeleteMapping("{name}")
    public void delete(@PathVariable("name") String name) {
        restaurantService.delete(name);
    }*/
}
