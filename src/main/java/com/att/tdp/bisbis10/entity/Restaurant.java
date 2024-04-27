package com.att.tdp.bisbis10.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(name = "average_rating")
    private float averageRating = 0;

    @Column(name = "is_kosher")
    @JsonProperty("isKosher")
    private boolean isKosher;

    @ElementCollection
    @CollectionTable(name = "restaurant_cuisines", joinColumns = @JoinColumn(name = "restaurant_id"))
    @Column(name = "cuisines")
    private List<String> cuisines = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Dish> dishes = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Rating> ratings = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

    public Restaurant() {}

    public Restaurant(String name, boolean isKosher, List<String> cuisines) {
        this.name = name;
        this.isKosher = isKosher;
        this.cuisines = cuisines;
    }

    public Long getId() { return restaurantId; }

    public void setId(Long id) { this.restaurantId = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public float getAverageRating() { return averageRating; }

    public void setAverageRating(float averageRating) { this.averageRating = averageRating; }

    public boolean isKosher() { return isKosher; }

    public void setIsKosher(boolean kosher) { isKosher = kosher; }

    public List<String> getCuisines() { return cuisines; }

    public void setCuisines(List<String> cuisines) { this.cuisines = new ArrayList<>(cuisines); }

    public List<Dish> getDishes() { return dishes; }

    public void setDishes(List<Dish> dishes) { this.dishes = dishes; }

    public List<Order> getOrders() { return orders; }

    public void setOrders(List<Order> orders) { this.orders = new ArrayList<>(orders); }

    public List<Rating> getRatings() { return ratings; }

    public void setRatings(List<Rating> ratings) { this.ratings = ratings; }

    public void updateAverageRating(Rating newRating) {
        if (ratings.isEmpty()) {
            averageRating = newRating.getRating();
            ratings.add(newRating);
        } else {
            int sum = 0;
            ratings.add(newRating);
            for (Rating rating : ratings) {
                sum += rating.getRating();
            }
            averageRating = (float)(sum / ratings.size());
        }
    }
}
