package com.att.tdp.bisbis10.entity;

import com.att.tdp.bisbis10.views.Views;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({"restaurantId", "name", "averageRating", "isKosher", "cuisines", "dishes"})
@Entity
@Table(name = "restaurants")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id")
    @JsonView(Views.RestaurantDetails.class)
    private Long restaurantId;

    @Column(nullable = false)
    @JsonView(Views.RestaurantDetails.class)
    private String name;

    @Column(name = "average_rating")
    @JsonView(Views.RestaurantDetails.class)
    private float averageRating = 0;

    @Column(name = "is_kosher")
    @JsonProperty("isKosher")
    @JsonView(Views.RestaurantDetails.class)
    private boolean isKosher;

    @ElementCollection
    @CollectionTable(name = "restaurant_cuisines", joinColumns = @JoinColumn(name = "restaurant_id"))
    @Column(name = "cuisine")
    @JsonView(Views.RestaurantDetails.class)
    private List<String> cuisines = new ArrayList<>();


    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    @JsonView({Views.RestaurantWithDishes.class, Views.DishDetails.class})
    private List<Dish> dishes = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Rating> ratings = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

    public Restaurant() {}

    public Restaurant(String name, boolean isKosher) {
        this.name = name;
        this.isKosher = isKosher;
        this.averageRating = 0;
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
        if (newRating != null) {
            if (ratings.isEmpty()) {
                averageRating = newRating.getRating();
                ratings.add(newRating);
            } else {
                float sum = 0;
                ratings.add(newRating);
                for (Rating rating : ratings) {
                    sum += rating.getRating();
                }
                averageRating = sum / ratings.size();
            }
        }
    }

}
