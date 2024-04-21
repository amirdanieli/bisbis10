package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.dto.DishDto;
import com.att.tdp.bisbis10.entity.Dish;
import com.att.tdp.bisbis10.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DishService {

    @Autowired
    private DishRepository dishRepository;

    public List<Dish> getDishesByRestaurant(Long restaurantId) {
        // Implement logic to retrieve dishes by restaurant ID from the database
        // Example:
        return dishRepository.findByRestaurantId(restaurantId);
    }

    public Dish addDish(Long restaurantId, DishDto dishDto) {
        // Implement logic to add a new dish to the database
        // Example:
        Dish dish = new Dish();
        dish.setName(dishDto.getName());
        dish.setDescription(dishDto.getDescription());
        dish.setPrice(dishDto.getPrice());
        dish.setRestaurantId(restaurantId);
        return dishRepository.save(dish);
    }

    public void updateDish(Long restaurantId, Long dishId, DishDto dishDto) {
        // Implement logic to update an existing dish in the database
        // Example:
        Dish existingDish = dishRepository.findByIdAndRestaurantId(dishId, restaurantId);
        if (existingDish != null) {
            existingDish.setName(dishDto.getName());
            existingDish.setDescription(dishDto.getDescription());
            existingDish.setPrice(dishDto.getPrice());
            dishRepository.save(existingDish);
        }
    }

    public void deleteDish(Long restaurantId, Long dishId) {
        // Implement logic to delete a dish from the database
        // Example:
        dishRepository.deleteByIdAndRestaurantId(dishId, restaurantId);
    }
}
