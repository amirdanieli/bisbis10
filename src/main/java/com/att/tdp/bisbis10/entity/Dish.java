package com.att.tdp.bisbis10.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "dishes")
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private float price;

    @Column(name = "restaurant_id", nullable = false)
    private Long restaurantId;


    public Dish() {
    }

    public Dish(String name, String description, float price, Long restaurantId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.restaurantId = restaurantId;
    }
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public float getPrice() { return price; }

    public void setPrice(float price) { this.price = price; }

    public Long getRestaurantId() { return restaurantId; }

    public void setRestaurantId(Long restaurantId) { this.restaurantId = restaurantId; }
}

