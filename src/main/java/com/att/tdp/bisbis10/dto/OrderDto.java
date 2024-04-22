package com.att.tdp.bisbis10.dto;

import com.att.tdp.bisbis10.entity.OrderItem;

import java.util.List;

public class OrderDto {

    private Long id;
    private List<OrderItem> orderItems;

    public OrderDto() {}

    public OrderDto(List<OrderItem> orderItems) { this.orderItems = orderItems; }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public List<OrderItem> getOrderItems() { return orderItems; }

    public void setOrderItems(List<OrderItem> orderItems) { this.orderItems = orderItems; }
}
