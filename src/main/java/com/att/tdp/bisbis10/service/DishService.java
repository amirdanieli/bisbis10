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

    public void addDish(Long restaurantId, DishDto dishDto) {
        // Implement logic to add a new dish to the database
        if (isValidDishDto(dishDto)) {
            Dish dish = new Dish(dishDto.getName(), dishDto.getDescription(), dishDto.getPrice(), restaurantId);
            dishRepository.save(dish);
        }
    }

    public void updateDish(Long restaurantId, Long dishId, DishDto dishDto) {
        Dish existingDish = dishRepository.findByIdAndRestaurantId(dishId, restaurantId);
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
        dishRepository.deleteByIdAndRestaurantId(dishId, restaurantId);
    }

    public List<Dish> getDishesByRestaurant(Long restaurantId) {
        // Implement logic to retrieve dishes by restaurant ID from the database
        // Example:
        return dishRepository.findAllByRestaurantId(restaurantId);
    }

    private boolean isValidDishDto(DishDto dishDto) {
        boolean validDishDto = true;

        if (dishDto.getName() == null || dishDto.getName().isEmpty()) {
            validDishDto = false;
        }
        else if (dishDto.getDescription() == null || dishDto.getDescription().isEmpty()) {
            validDishDto = false;
        }
        else if (dishDto.getPrice() <= 0.0 || dishDto.getPrice() > Float.MAX_VALUE) { //MAKE SURE TO VALIDATE REQUEST BODY CONTAINS FLOAT
            validDishDto = false;
        }

        return validDishDto;
    }

}


