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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    public Rating(){}

    public Rating(Restaurant restaurant, float rating) {
        this.restaurant = restaurant;
        this.restaurant.setNumberOfRatings(this.restaurant.getNumberOfRatings() + 1);
        setRating(rating);
    }

    public float getRating() { return rating; }

    public void setRating(float rating) {
        float averageRating = calculateAverageRating(rating);
        this.rating = rating;
    }

    private float calculateAverageRating(float rating) {
        int numberOfRatings = restaurant.getNumberOfRatings();
        if (numberOfRatings == 0) {
            return rating;
        } else {
            float totalRating = restaurant.getAverageRating().getRating() * (numberOfRatings - 1);
            totalRating += rating;
            return totalRating / numberOfRatings;
        }
    }
}
