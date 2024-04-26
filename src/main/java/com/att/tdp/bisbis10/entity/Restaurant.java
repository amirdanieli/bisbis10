package com.att.tdp.bisbis10.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({"id", "name", "averageRating", "isKosher", "cuisines", "dishes", "orders"})
@Entity
@Table(name = "restaurants")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id")
    private Long restaurantId;

    @Column(nullable = false)
    private String name;

    @OneToOne(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private Rating averageRating;

    @Column(name = "is_kosher")
    @JsonProperty("isKosher")
    private boolean isKosher;

    @ElementCollection
    @CollectionTable(name = "restaurant_cuisines", joinColumns = @JoinColumn(name = "restaurant_id"))
    @Column(name = "cuisine")
    private List<String> cuisines;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Dish> dishes;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Order> orders;

    public Restaurant() {
    }

    public Restaurant(String name, boolean isKosher, List<String> cuisines) {
        this.name = name;
        this.isKosher = isKosher;
        this.cuisines = cuisines;
    }

    public Long getId() { return restaurantId; }

    public void setId(Long id) { this.restaurantId = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Rating getAverageRating() { return averageRating; }

    public void setAverageRating(Rating averageRating) { this.averageRating = averageRating; }

    public boolean isKosher() { return isKosher; }

    public void setIsKosher(boolean kosher) { isKosher = kosher; }

    public List<String> getCuisines() { return cuisines; }

    public void setCuisines(List<String> cuisines) { this.cuisines = new ArrayList<>(cuisines); }

    public List<Dish> getDishes() { return dishes; }

    public void setDishes(List<Dish> dishes) { this.dishes = dishes; }

    public List<Order> getOrders() { return orders; }

    public void setOrders(List<Order> orders) { this.orders = new ArrayList<>(orders); }
}
