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

    @GetMapping("/{id}")
    public Restaurant getSingleRestaurantById(@PathVariable("id") UUID id) {
        return restaurantService.getRestaurantById(id);
    }

    @GetMapping("/{id}/meals")
    public Restaurant getRestaurantsWithMeals(@PathVariable("id") UUID restaurantId,
                                                    @RequestParam(required = false) Integer page, Sort.Direction sort) {
        int pageNumber = page != null && page > 0 ? page : 1;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        return restaurantService.getRestaurantsWithMeals(restaurantId,pageNumber - 1, sortDirection);
    }
    //zrobic metode restaurants/{id}/meals

    @PostMapping
    public Restaurant createRestaurantDto(@RequestBody RestaurantDto restaurantDto) {
        return restaurantService.addRestaurant(restaurantDto);
    }

    @PutMapping
    public Restaurant updateRestaurantDtoById(@RequestBody RestaurantDto restaurantDto) {
        return restaurantService.updateRestaurantById(restaurantDto);
    }

    @PatchMapping("/{name}")
    public Restaurant updateRestaurantDtoAddressByName(@PathVariable("name") String name, @RequestParam("address") String address) {
        return restaurantService.updateRestaurantAddressByName(name, address);
    }

    @DeleteMapping("/{id}")
    public void deleteRestaurant(@PathVariable("id") UUID id) {
        restaurantService.deleteRestaurant(id);
    }
}
