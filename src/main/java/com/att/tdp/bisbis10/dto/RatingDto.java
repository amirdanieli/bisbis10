package com.att.tdp.bisbis10.dto;

public class RatingDto {
    private Long restaurantId;
    private float rating;

    public RatingDto(){}

    public RatingDto(Long restaurantId, float rating) { this.restaurantId = restaurantId; this.rating = rating; }

    public Long getRestaurantId() { return this.restaurantId; }

    public void setRestaurantId() { this.restaurantId = restaurantId; }

    public float getRating() { return rating; }

    public void setRating(float rating) {
        this.rating = rating;
    }

}
