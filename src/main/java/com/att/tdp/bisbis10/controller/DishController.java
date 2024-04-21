package com.att.tdp.bisbis10.controller;

import com.att.tdp.bisbis10.dto.DishDto;
import com.att.tdp.bisbis10.entity.Dish;
import com.att.tdp.bisbis10.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/restaurants/{restaurantId}/dishes")
public class DishController {

    @Autowired
    private DishService dishService;

    @PostMapping
    public ResponseEntity<Void> addDish(@PathVariable("restaurantId") Long restaurantId, @RequestBody DishDto dishDto) {
        try {
            validateDishDto(dishDto); //MAKE SURE TO VALIDATE ALL EDGE CASES!

            dishService.addDish(restaurantId, dishDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{dishId}")
    public ResponseEntity<Void> updateDish(@PathVariable("restaurantId") Long restaurantId, @PathVariable("dishId") Long dishId, @RequestBody DishDto dishDto) {
        dishService.updateDish(restaurantId, dishId, dishDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{dishId}")
    public ResponseEntity<Void> deleteDish(@PathVariable("restaurantId") Long restaurantId, @PathVariable("dishId") Long dishId) {
        dishService.deleteDish(restaurantId, dishId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Dish>> getDishesByRestaurant(@PathVariable("restaurantId") Long restaurantId) {
        List<Dish> dishes = dishService.getDishesByRestaurant(restaurantId);
        return ResponseEntity.ok(dishes);
    }

    private void validateDishDto(DishDto dishDto) {
        if (dishDto.getName() == null || dishDto.getName().isEmpty()) {
            throw new IllegalArgumentException("Name is required");
        }
        if (dishDto.getDescription() == null || dishDto.getDescription().isEmpty()) {
            throw new IllegalArgumentException("Description is required");
        }
        if (dishDto.getPrice() <= 0 || dishDto.getPrice() > Float.MAX_VALUE) { //MAKE SURE TO VALIDATE REQUEST BODY CONTAINS FLOAT
            throw new IllegalArgumentException("Price must be greater than 0 and smaller than: " + Float.MAX_VALUE);
        }
    }
}
