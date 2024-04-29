package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.dto.OrderDto;
import com.att.tdp.bisbis10.entity.Dish;
import com.att.tdp.bisbis10.entity.Order;
import com.att.tdp.bisbis10.entity.OrderItem;
import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final RestaurantService restaurantService;

    @Autowired
    public OrderService(OrderRepository orderRepository, RestaurantService restaurantService) {
        this.orderRepository = orderRepository;
        this.restaurantService = restaurantService;
    }

    public ResponseEntity<String> createOrder(OrderDto orderDto) {
        boolean invalidOrderItems = false;
        Restaurant restaurant = restaurantService.getRestaurantById(orderDto.getRestaurantId());
        if (restaurant == null) {
            return ResponseEntity.badRequest().body("order not created, restaurant doesn't exist!");

        }

        Order order = new Order();
        order.setRestaurant(restaurant);

        HashSet<Long> idSet = createDishesInRestaurantSet(restaurant);
        if (idSet.isEmpty() || noDishesExistInRestaurant(idSet, orderDto)) {
            return ResponseEntity.badRequest().body("None of the provided order ID's exist in the restaurant.");
        }

        Order savedOrder = orderRepository.save(order);

        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItem orderItem : orderDto.getOrderItems()) {
            if (idSet.contains(orderItem.getDishId())) {
                orderItem.setOrder(savedOrder);
                orderItems.add(orderItem);
            } else {
                invalidOrderItems = true;
            }
        }

        order.setOrderItems(orderItems);
        orderRepository.save(savedOrder);

        if (invalidOrderItems) {
            return ResponseEntity.ok("orderId: \"" + savedOrder.getId() + "\"" + '\n' + "Not all order items" +
                    " have been added. Please only add dishes that exist in the restaurant.");

        }
        return ResponseEntity.ok("orderId: \"" + savedOrder.getId() + "\"");
    }

    private boolean noDishesExistInRestaurant(HashSet<Long> set, OrderDto orderDto) {
        for (OrderItem orderItem : orderDto.getOrderItems()) {
            if (set.contains(orderItem.getDishId())) {
                return false;
            }
        }

        return true;
    }

    private HashSet<Long> createDishesInRestaurantSet(Restaurant restaurant) {
        HashSet<Long> idSet = new HashSet<>();
        List<Dish> restaurantDishes = restaurant.getDishes();
        for (Dish dish : restaurantDishes) {
            idSet.add(dish.getId());
        }

        return idSet;
    }
}
