package com.restaurant.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String name;
    private String address;
    @Enumerated(EnumType.STRING)
    private RestaurantType type;
    @OneToMany(cascade = CascadeType.REMOVE)//(orphanRemoval = true)
    @JoinColumn(name = "restaurantId", updatable = false, insertable = false)
    private List<Meal> meals;

    public Restaurant(String restaurantName, String restaurantAddress, RestaurantType type) {
        this.id = UUID.randomUUID();
        this.name = restaurantName;
        this.address = restaurantAddress;
        this.type = type;
    }
    //@JoinColumn(name = "id")

    @Override
    public String toString() {
        return "Restaurant{" +
                "id = " + id +
                ", name = '" + name + '\'' +
                ", address = '" + address + '\'' +
                ", type = " + type +
                '}';
    }
}

