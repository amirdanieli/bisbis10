package com.att.tdp.bisbis10.controller;

import com.att.tdp.bisbis10.dto.RestaurantDto;
import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.service.RestaurantService;
import com.att.tdp.bisbis10.views.Views;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.View;
import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
    @JsonView(Views.RestaurantDetails.class)
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantService.getAllRestaurants();

        return ResponseEntity.ok(restaurants);
    }

    @GetMapping(params = "cuisine")
    @JsonView(Views.RestaurantDetails.class)
    public ResponseEntity<List<Restaurant>> getRestaurantsByCuisine(@RequestParam("cuisine") String cuisine) {
        List<Restaurant> restaurants = restaurantService.getRestaurantsByCuisine(cuisine);

        return ResponseEntity.ok(restaurants);
    }

    @GetMapping("/{id}")
    @JsonView(Views.RestaurantWithDishes.class)
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable("id") Long restaurantId) {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);

        return ResponseEntity.ok(restaurant);
    }

    @PostMapping
    public ResponseEntity<Void> addRestaurant(@RequestBody RestaurantDto restaurantDto) {
        boolean restaurantAdded = restaurantService.addRestaurant(restaurantDto);

        return restaurantAdded ? ResponseEntity.status(HttpStatus.CREATED).build() : ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateRestaurant(@PathVariable("id") Long restaurantId, @RequestBody RestaurantDto restaurantDto) {
        boolean restaurantUpdated = restaurantService.updateRestaurant(restaurantId, restaurantDto);

        return restaurantUpdated ?  ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable("id") Long restaurantId) {
        restaurantService.deleteRestaurant(restaurantId);

        return ResponseEntity.noContent().build();
    }
}


