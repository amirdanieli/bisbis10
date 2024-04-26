package com.att.tdp.bisbis10.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<OrderItem> orderItems;

    public Order() {}

    public Order(List<OrderItem> orderItems) { this.orderItems = orderItems; }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public List<OrderItem> getOrderItems() { return orderItems; }

    public void setOrderItems(List<OrderItem> orderItems) { this.orderItems = orderItems; }
}
