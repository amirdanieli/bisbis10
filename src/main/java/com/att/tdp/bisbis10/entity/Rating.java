package com.att.tdp.bisbis10.entity;

import jakarta.persistence.*;

@Entity
@Table(name="ratings")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private float rating;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Restaurant restaurant;

    public Rating(){}

    public Rating(float rating) { this.rating = rating; }

    public float getRating() { return rating; }

    public void setRating(float rating) { this.rating = rating; }
}
