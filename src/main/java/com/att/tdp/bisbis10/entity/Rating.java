package com.att.tdp.bisbis10.entity;

import jakarta.persistence.*;

@Entity
@Table(name="ratings")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private float rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    public Rating(){}

    public Rating(Restaurant restaurant, float rating) {
        this.restaurant = restaurant;
        this.rating = rating;
    }

    public float getRating() { return rating; }

    public void setRating(float rating) { this.rating = rating; }

    public Restaurant getRestaurant() { return restaurant; }

    public void setRestaurant(Restaurant restaurant) { this.restaurant = restaurant; }
}
