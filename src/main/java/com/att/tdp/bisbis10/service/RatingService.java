package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.dto.RatingDto;
import com.att.tdp.bisbis10.entity.Rating;
import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.repository.RatingRepository;
import com.att.tdp.bisbis10.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;
    private final RestaurantService restaurantService;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RatingService(RatingRepository ratingRepository, RestaurantService restaurantService, RestaurantRepository restaurantRepository) {
        this.ratingRepository = ratingRepository;
        this.restaurantService = restaurantService;
        this.restaurantRepository = restaurantRepository;
    }

    public boolean addRating(RatingDto ratingDto) {
        boolean ratingAdded = false;
        if (isValidRatingDto(ratingDto)) {
            Restaurant restaurantToUpdate = restaurantService.getRestaurantById(ratingDto.getRestaurantId());
            if (restaurantToUpdate != null){
                Rating newRating = new Rating(restaurantToUpdate, ratingDto.getRating());
                restaurantToUpdate.updateAverageRating(newRating);
                ratingRepository.save(newRating);
                restaurantRepository.save(restaurantToUpdate);

                ratingAdded = true;
            }
        }

        return ratingAdded;
    }

    private boolean isValidRatingDto(RatingDto ratingDto) {
        return !(ratingDto.getRating() < 0) && !(ratingDto.getRating() > 5);
    }
}
