package com.restaurant.repository;

import com.restaurant.model.Restaurant;
import com.restaurant.model.RestaurantType;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public class RestaurantRepository {

    public final Set<Restaurant> restaurantsList;

    public RestaurantRepository(Set<Restaurant> restaurants) {
        this.restaurantsList = restaurants;
    }

    public RestaurantRepository() {
        this.restaurantsList = new HashSet<Restaurant>(List.of(new Restaurant("Ciastkarnia", "Warszawa", RestaurantType.POLISH)));
    }

    // Create
    public Restaurant add(Restaurant restaurant) {
        restaurantsList.add(restaurant);
        return restaurant;
    }

    // Read
    public Set<Restaurant> getAllRestaurants() {
        return restaurantsList;
    }

    public Restaurant updateRestaurantAddressByName(String restaurantName, String newRestaurantAddress) {
        for (final var restaurant : restaurantsList) {
            if (restaurant.getName().equals(restaurantName)) {
                restaurant.setAddress(newRestaurantAddress);
                System.out.println("Restaurant updated");
                return restaurant;
            }
        }
        throw new IllegalStateException();
    }

    // Delete
    public void delete(String name) {
        for (final var restaurant : restaurantsList) {
            if (restaurant.getName().equals(name)) {
                restaurantsList.remove(restaurant);
                System.out.println("Restaurant deleted");
            }
        }
    }

    public boolean isRestaurantListEmpty() {
        return getAllRestaurants().isEmpty();
    }

    public void updateRestaurantNameAndTypeById(UUID restaurantId, String name, RestaurantType type) {
        for (final var restaurant : getAllRestaurants()) {
            if (restaurant.getId().equals(restaurantId) && name != null && type != null) {
                restaurant.setName(name);
                restaurant.setType(type);
                System.out.println("Restaurant updated");
                return;
            }
        }
        throw new IllegalStateException();
    }
}
