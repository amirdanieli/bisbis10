package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.dto.RestaurantDto;
import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.repository.RestaurantRepositroy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RestaurantService {

    private final RestaurantRepositroy restaurantRepositroy;

    @Autowired
    public RestaurantService(RestaurantRepositroy restaurantRepositroy) {
        this.restaurantRepositroy = restaurantRepositroy;
    }

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepositroy.findAll();
    }

    public List<Restaurant> getRestaurantsByCuisine(String cuisine) {
        return restaurantRepositroy.findAllByCuisine(cuisine);
    }

    public Restaurant getRestaurantById(Long restaurantId) {
        return restaurantRepositroy.findByRestaurantId(restaurantId);
    }

    public void addRestaurant(RestaurantDto restaurantDto) {
        if (isValidRestaurantDto(restaurantDto)) {
            Restaurant restaurant = new Restaurant(restaurantDto.getName(), restaurantDto.getAverageRating(),
                    restaurantDto.isKosher(), restaurantDto.getCuisines(), restaurantDto.getDishes());
            restaurantRepositroy.save(restaurant);
        }
    }

    public void updateRestaurant(Long restaurantId, RestaurantDto restaurantDto) {
        Restaurant existingRestaurant = restaurantRepositroy.findByRestaurantId(restaurantId);
        if (existingRestaurant != null) { //If there is a matching restaurant in the DB to update
            existingRestaurant.setName(restaurantDto.getName() != null ?
                    restaurantDto.getName() : existingRestaurant.getName());
            existingRestaurant.setAverageRating(restaurantDto.getAverageRating() != null ?
                    restaurantDto.getAverageRating() : existingRestaurant.getAverageRating());
            existingRestaurant.setKosher(restaurantDto.isKosher()); //Assumes that a restaurant is not kosher unless stated otherwise
            existingRestaurant.setCuisines(restaurantDto.getCuisines().isEmpty() ?
                    existingRestaurant.getCuisines() : restaurantDto.getCuisines());
            existingRestaurant.setDishes(restaurantDto.getDishes() != null ?
                    restaurantDto.getDishes() : existingRestaurant.getDishes());

            restaurantRepositroy.save(existingRestaurant);
        }
    }

    public void deleteRestaurant(Long restaurantId) {
        restaurantRepositroy.deleteById(restaurantId);
    }

    private boolean isValidRestaurantDto(RestaurantDto restaurantDto) {
        return restaurantDto.getName() != null &&
                restaurantDto.getAverageRating() != null && restaurantDto.getAverageRating().getRating() > 0 &&
                restaurantDto.getAverageRating().getRating() < 5;
    }
}
