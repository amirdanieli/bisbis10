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
                    restaurantDto.isKosher(), restaurantDto.getCuisines());
            restaurantRepositroy.save(restaurant);
        }
    }

    public void updateRestaurant(Long restaurantId, RestaurantDto restaurantDto) {
        Restaurant existingRestaurant = restaurantRepositroy.findByRestaurantId(restaurantId);
        if (existingRestaurant != null) {
            existingRestaurant.setName(restaurantDto.getName() != null ?
                    restaurantDto.getName() : existingRestaurant.getName());
            existingRestaurant.setAverageRating(restaurantDto.getAverageRating() != 0.0 ? //need to see how we can handle this, maybe another boolean field
                    restaurantDto.getAverageRating() : existingRestaurant.getAverageRating());
            existingRestaurant.setKosher(restaurantDto.isKosher()); //Assumes that a restaurant is not kosher unless stated otherwise
            existingRestaurant.setCuisines(restaurantDto.getCuisines().isEmpty() ?
                    existingRestaurant.getCuisines() : restaurantDto.getCuisines());
        }
    }

    public void deleteRestaurant(Long restaurantId) {
        restaurantRepositroy.deleteById(restaurantId);
    }

    private boolean isValidRestaurantDto(RestaurantDto restaurantDto) {
        boolean restaurantDtoIsValid = restaurantDto.getName() != null &&
                !(restaurantDto.getAverageRating() < 0) && !(restaurantDto.getAverageRating() > 5);

        return restaurantDtoIsValid;
    }
}
