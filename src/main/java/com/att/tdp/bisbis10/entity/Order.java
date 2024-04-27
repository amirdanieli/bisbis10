package com.att.tdp.bisbis10.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    @JsonIgnore
    private Restaurant restaurant;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    public Order() {}

    public Order(List<OrderItem> orderItems, Restaurant restaurant) {
        this.orderItems = orderItems;
        this.restaurant = restaurant;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public List<OrderItem> getOrderItems() { return orderItems; }

    public void setOrderItems(List<OrderItem> orderItems) { this.orderItems = new ArrayList<>(orderItems); }

    public Restaurant getRestaurant() { return this.restaurant; }

    public void setRestaurant(Restaurant restaurant) { this.restaurant = restaurant; }
}
