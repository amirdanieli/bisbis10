package com.att.tdp.bisbis10.entity;

public class OrderItem {

    private int dishId;
    private int amount;

    public OrderItem(int dishId, int amount) {
        this.dishId = dishId;
        this.amount = amount;
    }

    public int getDishId() { return dishId; }

    public void setDishId(int dishId) { this.dishId = dishId; }

    public int getAmount() { return amount; }

    public void setAmount(int amount) { this.amount = amount; }
}
