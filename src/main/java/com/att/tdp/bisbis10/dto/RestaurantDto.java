package com.att.tdp.bisbis10.dto;

import java.util.List;

public class RestaurantDto {
    private String name;
    private float averageRating;
    private boolean isKosher;
    private List<String> cuisines;

    public RestaurantDto() {
    }

    public RestaurantDto(String name, float averageRating, boolean isKosher, List<String> cuisines) {
        this.name = name;
        this.averageRating = averageRating;
        this.isKosher = isKosher;
        this.cuisines = cuisines;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public float getAverageRating() { return averageRating; }

    public void setAverageRating(float averageRating) { this.averageRating = averageRating; }

    public boolean isKosher() { return isKosher; }

    public void setKosher(boolean kosher) { isKosher = kosher; }

    public List<String> getCuisines() { return cuisines; }

    public void setCuisines(List<String> cuisines) { this.cuisines = cuisines; }
}
