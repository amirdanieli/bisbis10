package com.att.tdp.bisbis10.dto;

import com.att.tdp.bisbis10.entity.Dish;
import com.att.tdp.bisbis10.entity.Rating;

import java.util.List;

public class RestaurantDto {
    private String name;
    private Rating averageRating;
    private boolean isKosher;
    private List<String> cuisines;
    private List<Dish> dishes;

    public RestaurantDto() {
    }

    public RestaurantDto(String name, boolean isKosher, List<String> cuisines) {
        this.name = name;
        this.isKosher = isKosher;
        this.cuisines = cuisines;
    }

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
