package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.dto.DishDto;
import com.att.tdp.bisbis10.entity.Dish;
import com.att.tdp.bisbis10.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DishService {

    private final DishRepository dishRepository;

    @Autowired
    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public List<Dish> getDishesByRestaurant(Long restaurantId) {
        // Implement logic to retrieve dishes by restaurant ID from the database
        // Example:
        return dishRepository.findByRestaurantId(restaurantId);
    }

    public void addDish(Long restaurantId, DishDto dishDto) {
        // Implement logic to add a new dish to the database
        try {
            validateDishDto(dishDto); //Check + work with edge cases
            Dish dish = new Dish(dishDto.getName(), dishDto.getDescription(), dishDto.getPrice(), restaurantId);
            dishRepository.save(dish);
        } catch (IllegalArgumentException e) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); //Check how to log (or something) bad request..
        }
    }

    public void updateDish(Long restaurantId, Long dishId, DishDto dishDto) {
        Dish existingDish = dishRepository.findByIdAndRestaurantId(restaurantId, dishId);
        if (existingDish != null) {
            existingDish.setName(dishDto.getName() != null ? dishDto.getName() : existingDish.getName());
            existingDish.setDescription(dishDto.getDescription() != null ? dishDto.getDescription() : existingDish.getDescription());
            if (dishDto.getPrice() > 0.0 && dishDto.getPrice() <= Float.MAX_VALUE) {
                existingDish.setPrice(dishDto.getPrice());
            }
            dishRepository.save(existingDish);
        }
    }

    public void deleteDish(Long restaurantId, Long dishId) {
        dishRepository.deleteByIdAndRestaurantId(restaurantId, dishId);
    }

    private void validateDishDto(DishDto dishDto) {
        if (dishDto.getName() == null || dishDto.getName().isEmpty()) {
            throw new IllegalArgumentException("Name is required");
        }
        if (dishDto.getDescription() == null || dishDto.getDescription().isEmpty()) {
            throw new IllegalArgumentException("Description is required");
        }
        if (dishDto.getPrice() <= 0.0 || dishDto.getPrice() > Float.MAX_VALUE) { //MAKE SURE TO VALIDATE REQUEST BODY CONTAINS FLOAT
            throw new IllegalArgumentException("Price must be greater than 0 and smaller than: " + Float.MAX_VALUE);
        }
    }
}


