package com.restaurant.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurant.model.Restaurant;
import com.restaurant.repository.RestaurantRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static com.restaurant.model.RestaurantType.MEDITERRANEAN;
import static com.restaurant.model.RestaurantType.POLISH;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    /*private Restaurant newRestaurant;

    @BeforeEach
    public void beforeEach() {
        newRestaurant = new Restaurant();
        newRestaurant.setName("Kebab");
        newRestaurant.setAddress("Warszawa");
        newRestaurant.setType(ASIAN);
        //restaurant.setMeals();
        restaurantRepository.save(newRestaurant);
    }*/


    @Test
        //dla malej ilosci danych
    void shutGetSingleRestaurant() throws Exception {

        //given
        Restaurant newRestaurant = new Restaurant();
        newRestaurant.setName("U Alberta");
        newRestaurant.setAddress("Kraków");
        newRestaurant.setType(POLISH);
        //restaurant.setMeals();
        restaurantRepository.save(newRestaurant);

        //when
        mockMvc.perform(get("/restaurants/" + newRestaurant.getId()))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", Matchers.is(newRestaurant.getId().toString())));
        //then
    }

    @Test
        //dla wiekszej ilosci danych
    void shutGetRestaurantById2() throws Exception {
        //given
        Restaurant newRestaurant = new Restaurant();
        newRestaurant.setName("Ciastkarnia");
        newRestaurant.setAddress("Poznań");
        newRestaurant.setType(MEDITERRANEAN);
        //restaurant.setMeals();
        restaurantRepository.save(newRestaurant);
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


}