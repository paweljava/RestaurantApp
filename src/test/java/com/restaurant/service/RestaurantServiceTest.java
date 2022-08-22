package com.restaurant.service;

import com.restaurant.model.Restaurant;
import com.restaurant.repository.RestaurantRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static com.restaurant.model.RestaurantType.ASIAN;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RestaurantServiceTest {

    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Test
    void shouldRestaurantById() {
        //given
        Restaurant newRestaurant = new Restaurant();
        newRestaurant.setName("Bida");
        newRestaurant.setAddress("Lublin");
        newRestaurant.setType(ASIAN);
        //restaurant.setMeals();
        restaurantRepository.save(newRestaurant);

        //when
        Restaurant restaurantById = restaurantService.getRestaurantById(newRestaurant.getId());

        //then
        assertThat(restaurantById).isNotNull();
        assertThat(restaurantById.getId()).isEqualTo(newRestaurant.getId());
        assertThat(restaurantById.getName()).isEqualTo(newRestaurant.getName());
        assertThat(restaurantById.getName()).isEqualTo(newRestaurant.getName());
    }
}