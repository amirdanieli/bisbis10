package com.att.tdp.bisbis10.repository;

import com.att.tdp.bisbis10.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    
    List<Restaurant> findAllByCuisines(String cuisine);

    Restaurant findByRestaurantId(Long id);
}
