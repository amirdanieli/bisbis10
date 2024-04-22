package com.att.tdp.bisbis10.controller;

import com.att.tdp.bisbis10.dto.DishDto;
import com.att.tdp.bisbis10.entity.Dish;
import com.att.tdp.bisbis10.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants/{restaurantId}/dishes")
public class DishController {

    private final DishService dishService;

    @Autowired
    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @PostMapping
    public ResponseEntity<Void> addDish(@PathVariable("restaurantId") Long restaurantId, @RequestBody DishDto dishDto) {
        dishService.addDish(restaurantId, dishDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
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

}
