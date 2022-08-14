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
    private String name;
    private float price;

    public Meal(String name, float price) {
        this.name = name;
        this.price = price;
    }

    public UUID getId() {
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
    }

    @Override // po co jest to nadpisywanie ?
    public String toString() {
        return "Meal{" +
                "mealId=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
