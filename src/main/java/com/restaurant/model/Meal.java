package com.restaurant.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Meal {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private UUID restaurantId;
    private String name;
    private float price;

    public Meal(UUID restaurantId, String name, float price) {
        this.id = UUID.randomUUID();
        this.restaurantId = restaurantId;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "mealId=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
