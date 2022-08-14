package com.restaurant.repository;

import com.restaurant.model.Restaurant;
import com.restaurant.model.RestaurantDTO;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

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
        return jdbcTemplate.query(("SELECT r.id, r.name, r.address, rt.name AS type \n" +
                        "FROM restaurant r \n" +
                        "JOIN restaurant_type_to_restaurant rttr ON r.id = rttr.restaurant_id  \n" +
                        "JOIN restaurant_type rt ON rttr.restaurant_type_id = rt.id \n" +
                        "WHERE r.name = 'Kawiarnia' \n"),
                BeanPropertyRowMapper.newInstance(Restaurant.class));
    }

    public List<Restaurant> getAllRestaurantsAndMeals() {
        return jdbcTemplate.query(("SELECT r.id, r.name, r.address, rt.name AS type, add value in(m.name, m.price) as meallist \n" +
                        "FROM restaurant r \n" +
                        "JOIN restaurant_type_to_restaurant rttr ON r.id = rttr.restaurant_id  \n" +
                        "JOIN restaurant_type rt ON rttr.restaurant_type_id = rt.id \n" +
                        "JOIN meal_to_restaurant mtr ON r.id = mtr.restaurant_id \n" +
                        "JOIN meal m ON mtr.meal_id = m.id \n" +
                        "WHERE r.name = 'Pierogarnia' \n"),
                BeanPropertyRowMapper.newInstance(Restaurant.class));
    }
    //wersja poprzednia
    /*public List<Restaurant> getAllRestaurants() {
        return jdbcTemplate.query(("SELECT id, name, address FROM restaurant"),
                BeanPropertyRowMapper.newInstance(Restaurant.class));
    }*/

    public Restaurant getRestaurantById(UUID id) {
        return jdbcTemplate.queryForObject("SELECT id, name, address, type FROM restaurant WHERE " +
                "id=?", BeanPropertyRowMapper.newInstance(Restaurant.class), id);
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

    public Restaurant updateRestaurantAddressById(Restaurant restaurant) {
        jdbcTemplate.update("UPDATE restaurant SET address = ? WHERE id = ?",
                restaurant.getAddress(),
                restaurant.getId()
        );
        return restaurant;
    }

    //Delete
    public int deleteByName(String name) {
        jdbcTemplate.update("DELETE FROM restaurant WHERE name = ?", name);
        return 1;
    }

    /*public Meal addMealByRestaurantName() {
        jdbcTemplate.update("INSERT INTO meal(id, name, price) VALUES(?, ?, ?)",
                .getId(),
                restaurant.getName(),
                restaurant.getAddress(),
                restaurant.getType().toString()
        );
        return restaurant;
    }*/


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
