package com.restaurant.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Meal {
    private UUID id;
    private String name;
    private float price;

    public Meal(String name, float price) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.price = price;
    }

    /*public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }*/

    @Override // po co jest to nadpisywanie ?
    public String toString() {
        return "Meal{" +
                "mealId=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
