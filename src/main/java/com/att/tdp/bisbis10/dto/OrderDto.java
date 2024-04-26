package com.att.tdp.bisbis10.dto;

import com.att.tdp.bisbis10.entity.OrderItem;

import java.util.List;

public class OrderDto {

    private Long restaurantId;
    private List<OrderItem> orderItems;

    public OrderDto() {}

    public OrderDto(Long restaurantId, List<OrderItem> orderItems){
        this.restaurantId = restaurantId;
        this.orderItems = orderItems;
    }

    public List<OrderItem> getOrderItems() { return orderItems; }

    public void setOrderItems(List<OrderItem> orderItems) { this.orderItems = orderItems; }

    public Long getRestaurantId() { return restaurantId; }

    public void setRestaurantId(Long restaurantId) { this.restaurantId = restaurantId; }
}
