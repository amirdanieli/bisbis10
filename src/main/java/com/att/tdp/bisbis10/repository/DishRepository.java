package com.att.tdp.bisbis10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.att.tdp.bisbis10.entity.Dish;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {

    List<Dish> findAllByRestaurantId(Long restaurantId);

    Dish findByIdAndRestaurantId(Long id, Long restaurantId);

    void deleteByIdAndRestaurantId(Long id, Long restaurantId);
}
