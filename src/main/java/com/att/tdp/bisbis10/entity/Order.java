package com.att.tdp.bisbis10.entity;

import jakarta.persistence.*;

import java.util.HashMap;
import java.util.List;

@Entity
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Order id should look like the example
    private Long id;

    @Column(nullable = false)
    private List<OrderItem> orderItems;

    public Order(){}

    public Order(List<OrderItem> orderItems) { this.orderItems = orderItems; }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
