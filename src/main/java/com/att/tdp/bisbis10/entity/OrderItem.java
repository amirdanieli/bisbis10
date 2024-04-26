package com.att.tdp.bisbis10.entity;

import jakarta.persistence.*;

@Entity
@Table(name="order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int dishId;

    @Column(nullable = false)
    private int amount;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    public OrderItem() {}

    public OrderItem(int dishId, int amount) {
        this.dishId = dishId;
        this.amount = amount;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public int getDishId() { return dishId; }

    public void setDishId(int dishId) { this.dishId = dishId; }

    public int getAmount() { return amount; }

    public void setAmount(int amount) { this.amount = amount; }

    public Order getOrder() { return order; }

    public void setOrder(Order order) { this.order = order; }
}
