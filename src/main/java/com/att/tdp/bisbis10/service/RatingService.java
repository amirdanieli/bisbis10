package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.dto.RatingDto;
import com.att.tdp.bisbis10.entity.Rating;
import com.att.tdp.bisbis10.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;
    private RestaurantService restaurantService;

    @Autowired
    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public void addRating(RatingDto ratingDto) {
        if (isValidRatingDto(ratingDto)) {
            Rating rating = new Rating(ratingDto.getRating());
            ratingRepository.save(rating);
        }
    }

    private boolean isValidRatingDto(RatingDto ratingDto) {
        return !(ratingDto.getRating() < 0) && !(ratingDto.getRating() > 5);
    }
}
