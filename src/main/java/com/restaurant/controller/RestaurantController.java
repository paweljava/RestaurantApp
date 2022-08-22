package com.restaurant.controller;

import com.restaurant.model.Restaurant;
import com.restaurant.model.RestaurantDto;
import com.restaurant.model.RestaurantDtoMapper;
import com.restaurant.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
    public List<RestaurantDto> getRestaurants(@RequestParam(required = false) Integer page, Sort.Direction sort) {
        int pageNumber = page != null && page > 0 ? page : 1;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        return RestaurantDtoMapper.mapToRestaurantDtos(restaurantService.getRestaurants(pageNumber - 1, sortDirection));
    }

    @GetMapping("/meals")
    public List<Restaurant> getRestaurantsWithMeals(@RequestParam(required = false) Integer page, Sort.Direction sort) {
        int pageNumber = page != null && page > 0 ? page : 1;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        return restaurantService.getRestaurantsWithMeals(pageNumber - 1, sortDirection);
    }

    @GetMapping("/{id}")
    public Restaurant getSingleRestaurantById(@PathVariable("id") UUID id) {
        return restaurantService.getRestaurantById(id);
    }

    @PostMapping
    public Restaurant addRestaurant(@RequestBody RestaurantDto restaurantDto) {
        return restaurantService.addRestaurant(restaurantDto);
    }

    //@PutMapping
    @PutMapping("/")
    public Restaurant updateRestaurantAddressByNamePut(@RequestBody Restaurant restaurant) {
        return restaurantService.editRestaurantAddressById(restaurant);
    }

    @DeleteMapping("/{id}")
    public void deleteRestaurant(@PathVariable("id") UUID id) {
        restaurantService.deleteRestaurant(id);
    }

    /*
    @PostMapping("/{name}")
    public Meal addMeal(@PathVariable("name") @RequestBody MealDto mealDTO) {
        return restaurantService.saveMeal(mealDTO);
    }*/

    /*@GetMapping("/{name}")
    public Restaurant getRestaurantByName(@RequestParam("name") String name) {
        return restaurantService.getRestaurantByName(name);
    }*/
    //@PatchMapping
    /*@PatchMapping("/{name}")
    public Restaurant updateRestaurantAddressByNamePatch(@PathVariable("name") String name, @RequestBody Restaurant restaurant) {
        return restaurantService.editRestaurantAddressByNamePatch(name, restaurant);
    }

    //@PutMapping
    @PutMapping("/{name}")
    public Restaurant updateRestaurantAddressByNamePut(@PathVariable("name") String name, @RequestBody Restaurant restaurant) {
        return restaurantService.editRestaurantAddressByNamePut(name, restaurant);
    }*/


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
