package com.att.tdp.bisbis10.controller;

import com.att.tdp.bisbis10.dto.RatingDto;
import com.att.tdp.bisbis10.entity.Rating;
import com.att.tdp.bisbis10.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    private final RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping
    public ResponseEntity<Void> addRating(@RequestBody RatingDto ratingDto) {
        ratingService.addRating(ratingDto);
        return ResponseEntity.ok().build();
    }
}
