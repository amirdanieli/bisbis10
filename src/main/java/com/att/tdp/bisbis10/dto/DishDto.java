package com.att.tdp.bisbis10.dto;

public class DishDto {
    private String name;
    private String description;
    private double price;
    private Long restaurantId;


    public DishDto() {
    }

    public DishDto(String name, String description, double price, Long restaurantId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }
}
