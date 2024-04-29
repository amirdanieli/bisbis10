package com.att.tdp.bisbis10.views;

public class Views {
    public interface RestaurantDetails {}
    public interface RestaurantWithDishes extends RestaurantDetails, DishDetails {}
    public interface DishDetails {}
}
