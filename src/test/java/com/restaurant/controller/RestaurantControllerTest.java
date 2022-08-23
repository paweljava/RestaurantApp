package com.restaurant.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurant.model.Restaurant;
import com.restaurant.model.RestaurantType;
import com.restaurant.repository.RestaurantRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.restaurant.model.RestaurantType.AMERICAN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser //stosujemy takze jesli aplikacja jest zabezpieczona tokenem
class RestaurantControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private RestaurantRepository restaurantRepository;

    private Restaurant newRestaurant;

    @BeforeEach
    public void beforeEach() {
        newRestaurant = new Restaurant();
        newRestaurant.setName("Kebab");
        newRestaurant.setAddress("Warszawa");
        newRestaurant.setType(AMERICAN);
        //restaurant.setMeals();
        restaurantRepository.save(newRestaurant);
    }


    @Test
        //dla malej ilosci danych
    void should_get_single_restaurant_by_id() throws Exception {
        //given
        /*Restaurant newRestaurant = new Restaurant();
        newRestaurant.setName("U Alberta");
        newRestaurant.setAddress("Kraków");
        newRestaurant.setType(POLISH);
        //restaurant.setMeals();
        restaurantRepository.save(newRestaurant);*/

        //when
        mockMvc.perform(get("/restaurants/" + newRestaurant.getId()))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", Matchers.is(newRestaurant.getId().toString())));
        //then
    }

    @Test
        //dla wiekszej ilosci danych
    void should_get_single_restaurant_by_id2() throws Exception {
        //given
        /*Restaurant newRestaurant = new Restaurant();
        newRestaurant.setName("Ciastkarnia");
        newRestaurant.setAddress("Poznań");
        newRestaurant.setType(MEDITERRANEAN);
        //restaurant.setMeals();
        restaurantRepository.save(newRestaurant);*/
        //final var mealsSize = (newRestaurant.getMeals()).size();

        //when
        final var mvcResult = mockMvc.perform(get("/restaurants/" + newRestaurant.getId()))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();

        //then
        final var restaurant = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Restaurant.class);
        assertThat(restaurant).isNotNull();
        assertThat(restaurant.getId()).isEqualTo(newRestaurant.getId());
        assertThat(restaurant.getName()).isEqualTo(newRestaurant.getName());
        assertThat(restaurant.getName()).isEqualTo(newRestaurant.getName());
        //assertThat(restaurant.getMeals()).hasSize(mealsSize);

    }

    @Test
    void addRestaurant() {
        //given
        //when
        //then
    }

    /*@Test
    void should_update_restaurant_dto_by_id() throws Exception {
        //given
        final var newName = "GrillBar";
        final var newAddress = "Kielce";
        final var newType = RestaurantType.MEDITERRANEAN;
        //when
        var mvcResult = mockMvc.perform(put("/restaurants/" + newRestaurant.getId())
                        .param("address", newAddress))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();
        //then
        final var restaurant = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Restaurant.class);
        assertThat(restaurant).isNotNull();
        assertThat(restaurant.getId()).isEqualTo(newRestaurant.getId());
        assertThat(restaurant.getName()).isEqualTo(newRestaurant.getName());
        assertThat(restaurant.getAddress()).isEqualTo(newAddress);
    }*/

    @Test
    void should_update_restaurant_dto_address_by_name() throws Exception {
        //given
        final var newAddress = "Kielce";
        //when
        var mvcResult = mockMvc.perform(patch("/restaurants/" + newRestaurant.getName())
                .param("address", newAddress))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();
        //then
        final var restaurant = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Restaurant.class);
        assertThat(restaurant).isNotNull();
        assertThat(restaurant.getId()).isEqualTo(newRestaurant.getId());
        assertThat(restaurant.getName()).isEqualTo(newRestaurant.getName());
        assertThat(restaurant.getAddress()).isEqualTo(newAddress);
    }

    @Test
    void should_delete_restaurant() {
        //given
        //when
        //then
        assertDoesNotThrow(() -> restaurantRepository.delete(newRestaurant));
    }
}