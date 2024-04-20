package com.att.tdp.bisbis10.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/restaurants/{restaurantId}/dishes")
public class DishController {

    @Autowired
    private DishService dishService;

    @PostMapping
    public ResponseEntity<Void> addDish(@PathVariable("restaurantId") Long restaurantId, @RequestBody DishDto dishDto) {
        Dish addedDish = dishService.addDish(restaurantId, dishDto);
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
