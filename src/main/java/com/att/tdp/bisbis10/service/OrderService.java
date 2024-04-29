package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.dto.OrderDto;
import com.att.tdp.bisbis10.entity.Order;
import com.att.tdp.bisbis10.entity.OrderItem;
import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.repository.OrderRepository;
import com.att.tdp.bisbis10.repository.RestaurantRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final RestaurantService restaurantService;
    private final RestaurantRepository restaurantRepository; //Should be final?

    @Autowired
    public OrderService(OrderRepository orderRepository, RestaurantService restaurantService,
                        RestaurantRepository restaurantRepository) {
        this.orderRepository = orderRepository;
        this.restaurantService = restaurantService;
        this.restaurantRepository = restaurantRepository;
    }

    public Order createOrder(OrderDto orderDto) {
        Restaurant restaurant = restaurantService.getRestaurantById(orderDto.getRestaurantId());

        Order order = new Order();
        order.setRestaurant(restaurant);

        Order savedOrder = orderRepository.save(order);

        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItem orderItem : orderDto.getOrderItems()) {
            orderItem.setOrder(savedOrder);
            orderItems.add(orderItem);
        }

        order.setOrderItems(orderItems);
        orderRepository.save(savedOrder);

        return savedOrder;
    }
}
