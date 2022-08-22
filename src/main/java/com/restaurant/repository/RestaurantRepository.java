package com.restaurant.repository;

import com.restaurant.model.Restaurant;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, UUID> {



    @Query("SELECT restaurant FROM Restaurant restaurant")
    List<Restaurant> findAllRestaurant(Pageable page);

    /*@Query("SELECT restaurant FROM Restaurant restaurant left join fetch restaurant.meals")
    List<Restaurant> findAllRestaurant(Pageable page);*/

    /*//@Query("SELECT restaurant FROM Restaurant restaurant WHERE name = :name")
    Restaurant findByName(@Param("name") String name);

    Restaurant findAllById(UUID id);*/

    //Restaurant getRestaurantByAddress(String address);
    /*@Query("UPDATE address FROM Restaurant WHERE name = :name")
    Restaurant updateRestaurantAddressByName(@Param("name") Restaurant restaurant);*/

    //Restaurant updateRestaurantAddressById(Restaurant restaurant);

    //void deleteByName(String name);
}
