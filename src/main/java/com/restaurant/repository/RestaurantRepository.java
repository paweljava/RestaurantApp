package com.restaurant.repository;

import com.restaurant.model.Restaurant;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RestaurantRepository {

    public final List<Restaurant> restaurantsList;
    public JdbcTemplate jdbcTemplate;


    public RestaurantRepository(List<Restaurant> restaurants, JdbcTemplate jdbcTemplate) {
        this.restaurantsList = restaurants;
        this.jdbcTemplate = jdbcTemplate;
    }

    /*public RestaurantRepository() {
        this.restaurantsList = new HashSet<Restaurant>(List.of(new Restaurant("Ciastkarnia", "Warszawa", RestaurantType.POLISH)));
    }*/

    //Create
    public Restaurant add(Restaurant restaurant) {
        jdbcTemplate.update("INSERT INTO restaurant(id, name, address, type) VALUES(?, ?, ?, ?)",
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getAddress(),
                restaurant.getType().toString()
        );
        return restaurant;
    }

    /*public List<Restaurant> add(List<Restaurant> restaurants) {
        restaurants.forEach(restaurant -> jdbcTemplate
                .update("INSERT INTO restaurant(id, name, address, type) VALUES(?, ?, ?, ?)",
                        restaurant.getId(),
                        restaurant.getName(),
                        restaurant.getAddress(),
                        restaurant.getType()
                ));
        return restaurants;
    }*/

    //Read
    public List<Restaurant> getAllRestaurants() {
        return jdbcTemplate.query("SELECT id, name, address, type From restaurant",
                BeanPropertyRowMapper.newInstance(Restaurant.class));
    }

    public Restaurant getRestaurantByName(String name) {
        return jdbcTemplate.queryForObject("SELECT id, name, address, type FROM restaurant WHERE " +
                "name=?", BeanPropertyRowMapper.newInstance(Restaurant.class), name);
    }

    public Restaurant getRestaurantByAddress(String address) {
        return jdbcTemplate.queryForObject("SELECT id, name, address, type FROM restaurant WHERE " +
                "address=?", BeanPropertyRowMapper.newInstance(Restaurant.class), address);
    }

    //Update
    public Restaurant updateRestaurantAddressByName(Restaurant restaurant) {
        jdbcTemplate.update("UPDATE restaurant SET address = ? WHERE name = ?",
                restaurant.getAddress(),
                restaurant.getName()
        );
        return restaurant;
    }


    //Delete
    public void deleteByName(String name) {
        Restaurant nameToDelete = null;
        for (final var restaurant : restaurantsList) {
            if (restaurant.getName().equals(name)) {
                nameToDelete = restaurant;
                //restaurantsList.remove(restaurant);
                //System.out.println("Restaurant deleted");
            }
        }
        restaurantsList.remove(nameToDelete);
    }


    public boolean isRestaurantListEmpty() {
        return getAllRestaurants().isEmpty();
    }

    /*public void updateRestaurantNameAndTypeById(UUID restaurantId, String name, RestaurantType type) {
        for (final var restaurant : getAllRestaurants()) {
            if (restaurant.getId().equals(restaurantId) && name != null && type != null) {
                restaurant.setName(name);
                restaurant.setType(type);
                System.out.println("Restaurant updated");
                return;
            }
        }
        throw new IllegalStateException();
    }*/
}
