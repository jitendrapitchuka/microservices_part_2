package com.jitendra.ratingservice.RatingService.Services;

import com.jitendra.ratingservice.RatingService.entity.Rating;

import java.util.List;

public interface RatingService {

    public Rating CreateRating(Rating rating);

    public List<Rating> getAllRatings();

    public List<Rating> getRatingByUserId(String userId);

    public List<Rating> getRatingByHotelId(String hotelId);

}
