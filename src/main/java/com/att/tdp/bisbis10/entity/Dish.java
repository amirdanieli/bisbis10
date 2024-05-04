package com.att.tdp.bisbis10.entity;

import com.att.tdp.bisbis10.views.Views;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;


@Entity
@Table(name = "dishes")
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.DishDetails.class)
    private Long id;

    @Column(nullable = false)
    @JsonView({Views.DishDetails.class})
    private String name;

    @Column(nullable = false)
    @JsonView(Views.DishDetails.class)
    private String description;

    @Column(nullable = false)
    @JsonView(Views.DishDetails.class)
    private float price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    public Dish() {
    }

    public Dish(String name, String description, float price, Restaurant restaurant) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.restaurant = restaurant;
    }
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public float getPrice() { return price; }

    public void setPrice(float price) { this.price = price; }
}

