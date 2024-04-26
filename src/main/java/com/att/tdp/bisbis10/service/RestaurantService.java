package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.dto.RestaurantDto;
import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public List<Restaurant> getRestaurantsByCuisine(String cuisine) {
        return restaurantRepository.findAllByCuisines(cuisine);
    }

    public Restaurant getRestaurantById(Long restaurantId) {
        return restaurantRepository.findByRestaurantId(restaurantId);
    }

    public void addRestaurant(RestaurantDto restaurantDto) {
        if (restaurantDto.getName() != null) {
            Restaurant restaurant = new Restaurant(restaurantDto.getName(), restaurantDto.isKosher(),
                    restaurantDto.getCuisines());
            restaurantRepository.save(restaurant);
        }
    }

    public void updateRestaurant(Long restaurantId, RestaurantDto restaurantDto) {
        Restaurant existingRestaurant = restaurantRepository.findByRestaurantId(restaurantId);
        if (existingRestaurant != null && restaurantDto != null) { //If there is a matching restaurant in the DB to update
            existingRestaurant.setName(restaurantDto.getName() != null ?
                    restaurantDto.getName() : existingRestaurant.getName());
            existingRestaurant.setAverageRating(restaurantDto.getAverageRating() != null ?
                    restaurantDto.getAverageRating() : existingRestaurant.getAverageRating());
            existingRestaurant.setIsKosher(restaurantDto.isKosher()); //Assumes that a restaurant is not kosher unless stated otherwise
            existingRestaurant.setCuisines(restaurantDto.getCuisines().isEmpty() ?
                    existingRestaurant.getCuisines() : restaurantDto.getCuisines());
            existingRestaurant.setDishes(restaurantDto.getDishes() != null ?
                    restaurantDto.getDishes() : existingRestaurant.getDishes());

            restaurantRepository.save(existingRestaurant);
        }
    }

    public void deleteRestaurant(Long restaurantId) {
        restaurantRepository.deleteById(restaurantId);
    }
}