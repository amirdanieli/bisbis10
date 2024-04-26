package com.att.tdp.bisbis10.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name="order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long dishId;

    @Column(nullable = false)
    private int amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id")
    private Order order;

    public OrderItem() {}

    public OrderItem(Long dishId, int amount) {
        this.dishId = dishId;
        this.amount = amount;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Long getDishId() { return dishId; }

    public void setDishId(Long dishId) { this.dishId = dishId; }

    public int getAmount() { return amount; }

    public void setAmount(int amount) { this.amount = amount; }

    public Order getOrder() { return order; }

    public void setOrder(Order order) { this.order = order; }
}
