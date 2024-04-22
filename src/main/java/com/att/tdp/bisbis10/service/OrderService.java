package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.dto.OrderDto;
import com.att.tdp.bisbis10.entity.Order;
import com.att.tdp.bisbis10.entity.OrderItem;
import com.att.tdp.bisbis10.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) { this.orderRepository = orderRepository; }

    public void placeOrder(OrderDto orderDto) {
        if (orderDtoIsValid(orderDto)) {
            Order order = createOrderFromDto(orderDto);
            orderRepository.save(order);
        }
    }

    private boolean orderDtoIsValid(OrderDto orderDto) {
        return orderDto.getId() != null && !(orderDto.getOrderItems().isEmpty());
    }

    private Order createOrderFromDto(OrderDto orderDto) {
        Order order = new Order();
        List<OrderItem> orderItems = new ArrayList<>(orderDto.getOrderItems());
        order.setOrderItems(orderItems);

        return order;
    }
}
