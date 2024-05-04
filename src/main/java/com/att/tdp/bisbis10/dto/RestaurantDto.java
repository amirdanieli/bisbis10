package com.att.tdp.bisbis10.dto;

import com.att.tdp.bisbis10.entity.Dish;
import com.att.tdp.bisbis10.entity.Rating;

import java.util.ArrayList;
import java.util.List;

public class RestaurantDto {
    private String name;
    private boolean isKosher;
    private List<String> cuisines;

    public RestaurantDto() {
    }

    public RestaurantDto(String name, boolean isKosher, List<String> cuisines) {
        this.name = name;
        this.isKosher = isKosher;
        this.cuisines = cuisines != null ? cuisines : new ArrayList<>();
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public boolean isKosher() { return isKosher; }

    public void setIsKosher(boolean isKosher) { this.isKosher = isKosher; }

    public List<String> getCuisines() { return cuisines != null ? cuisines : new ArrayList<>(); }

    public void setCuisines(List<String> cuisines) { this.cuisines = cuisines != null ? cuisines : new ArrayList<>(); }
}
