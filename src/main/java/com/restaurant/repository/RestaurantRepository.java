package com.restaurant.repository;

import com.restaurant.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, UUID> {

    Restaurant findByName(String name);
    Restaurant findAllById(UUID id);
    Restaurant getRestaurantByAddress(String address);

}
