package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.dto.OrderDto;
import com.att.tdp.bisbis10.entity.Order;
import com.att.tdp.bisbis10.entity.OrderItem;
import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.repository.OrderRepository;
import com.att.tdp.bisbis10.repository.RestaurantRepository;
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

    public void placeOrder(OrderDto orderDto) {
        if (orderDtoIsValid(orderDto)) {
            Order newOrder = createOrderFromDto(orderDto);
            Restaurant restaurantToAddOrder =  restaurantService.getRestaurantById(orderDto.getRestaurantId());
            if (restaurantToAddOrder != null) { //Only add newOrder if restaurant exists
                List<Order> restaurantOrders = restaurantToAddOrder.getOrders();
                restaurantOrders.add(newOrder);
                restaurantToAddOrder.setOrders(restaurantOrders);
                orderRepository.save(newOrder);
                restaurantRepository.save(restaurantToAddOrder);
            }
        }
    }

    private boolean orderDtoIsValid(OrderDto orderDto) {
        return orderDto.getRestaurantId() != null && !(orderDto.getOrderItems().isEmpty());
    }

    private Order createOrderFromDto(OrderDto orderDto) {
        Order order = new Order();
        List<OrderItem> orderItems = new ArrayList<>(orderDto.getOrderItems());
        order.setOrderItems(orderItems);

        return order;
    }
}
