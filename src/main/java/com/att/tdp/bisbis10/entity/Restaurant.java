package com.att.tdp.bisbis10.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "restaurants")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Rating averageRating;

    @Column(nullable = false)
    private boolean isKosher;

    @Column(nullable = false)
    private List<String> cuisines;

    @Column(nullable = false)
    private List<Dish> dishes;

    public Restaurant() {
    }

    public Restaurant(String name, Rating averageRating, boolean isKosher,
                      List<String> cuisines, List<Dish> dishes) {
        this.name = name;
        this.averageRating = averageRating;
        this.isKosher = isKosher;
        this.cuisines = cuisines;
        this.dishes = dishes;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Rating getAverageRating() { return averageRating; }

    public void setAverageRating(Rating averageRating) { this.averageRating = averageRating; }

    public boolean isKosher() { return isKosher; }

    public void setKosher(boolean kosher) { isKosher = kosher; }

    public List<String> getCuisines() { return cuisines; }

    public void setCuisines(List<String> cuisines) { this.cuisines = cuisines; }

    public List<Dish> getDishes() { return dishes; }

    public void setDishes(List<Dish> dishes) { this.dishes = dishes; }
}
