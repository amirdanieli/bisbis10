package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.dto.DishDto;
import com.att.tdp.bisbis10.entity.Dish;
import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.repository.DishRepository;
import com.att.tdp.bisbis10.repository.RestaurantRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class DishService {

    private final DishRepository dishRepository;
    private final RestaurantService restaurantService;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public DishService(DishRepository dishRepository, RestaurantService restaurantService,
                       RestaurantRepository restaurantRepository) {
        this.dishRepository = dishRepository;
        this.restaurantService = restaurantService;
        this.restaurantRepository = restaurantRepository;
    }

    public void addDish(Long restaurantId, DishDto dishDto) {
        if (isValidDishDto(dishDto)) {
            Restaurant restaurantToAddDish = restaurantService.getRestaurantById(restaurantId);
            if (restaurantToAddDish != null){
                Dish dish = new Dish(dishDto.getName(), dishDto.getDescription(), dishDto.getPrice(), restaurantToAddDish); //Moved inside the if, check if working. TBD
                List<Dish> restaurantDishes = restaurantToAddDish.getDishes();
                restaurantDishes.add(dish);
                restaurantToAddDish.setDishes(restaurantDishes);
                dishRepository.save(dish);
                restaurantRepository.save(restaurantToAddDish);
            }
        }
    }

    public void updateDish(Long restaurantId, Long dishId, DishDto dishDto) {
        Dish existingDish = dishRepository.findByIdAndRestaurantId(dishId, restaurantId);
        if (existingDish != null && dishDto != null) {
            existingDish.setName(dishDto.getName() != null ? dishDto.getName() : existingDish.getName());
            existingDish.setDescription(dishDto.getDescription() != null ?
                    dishDto.getDescription() : existingDish.getDescription());
            if (dishDto.getPrice() > 0.0 && dishDto.getPrice() <= Float.MAX_VALUE) {
                existingDish.setPrice(dishDto.getPrice());
            }
            dishRepository.save(existingDish);
        }
    }

    @Transactional
    public void deleteDish(Long restaurantId, Long dishId) {
        Restaurant restaurantToDeleteFrom = restaurantService.getRestaurantById(restaurantId);
        if (restaurantToDeleteFrom != null){
            List<Dish> restaurantDishes = restaurantToDeleteFrom.getDishes();
            Iterator<Dish> iterator = restaurantDishes.iterator();
            while (iterator.hasNext()) {
                Dish dish = iterator.next();
                if (dish.getId().equals(dishId)) {
                    iterator.remove();
                    break;
                }
            }
            restaurantToDeleteFrom.setDishes(restaurantDishes);
            restaurantRepository.save(restaurantToDeleteFrom);
            dishRepository.deleteByIdAndRestaurantId(dishId, restaurantId);
        }
    }

    public List<Dish> getDishesByRestaurant(Long restaurantId) {
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


