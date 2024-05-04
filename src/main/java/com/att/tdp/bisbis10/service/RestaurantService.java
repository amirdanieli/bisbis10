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

    public List<Restaurant> getAllRestaurants() { return restaurantRepository.findAll(); }

    public List<Restaurant> getRestaurantsByCuisine(String cuisine) {
        return restaurantRepository.findAllByCuisines(cuisine);
    }

    public Restaurant getRestaurantById(Long restaurantId) {
        return restaurantRepository.findByRestaurantId(restaurantId);
    }

    public boolean addRestaurant(RestaurantDto restaurantDto) {
        boolean restaurantAdded = false;

        if (restaurantDto.getName() != null) {
            Restaurant restaurant = new Restaurant(restaurantDto.getName(), restaurantDto.isKosher(),
                    restaurantDto.getCuisines());
            restaurantRepository.save(restaurant);
            restaurantAdded = true;
        }

        return restaurantAdded;
    }

    public boolean updateRestaurant(Long restaurantId, RestaurantDto restaurantDto) {
        boolean restaurantUpdated = false;

        Restaurant existingRestaurant = restaurantRepository.findByRestaurantId(restaurantId);
        if (existingRestaurant != null && restaurantDto != null) {
            existingRestaurant.setName(restaurantDto.getName() != null ? // Checks if new name to update, and updates accordingly
                    restaurantDto.getName() : existingRestaurant.getName());
            existingRestaurant.setIsKosher(restaurantDto.isKosher()); //Assumes that a restaurant is not kosher if not stated
            existingRestaurant.setCuisines(!(restaurantDto.getCuisines().isEmpty()) ?
                    restaurantDto.getCuisines() : existingRestaurant.getCuisines()); // Checks if new cuisines to update, and updates accordingly

            restaurantRepository.save(existingRestaurant);

            restaurantUpdated = true;
        }

        return restaurantUpdated;
    }

    public void deleteRestaurant(Long restaurantId) {
        restaurantRepository.deleteById(restaurantId);
    }
}