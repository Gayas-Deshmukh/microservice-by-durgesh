package com.rating.service;

import java.util.List;

import com.rating.dto.RatingDTO;

public interface RatingService
{
   RatingDTO addRating(RatingDTO ratingDTO);
   List<RatingDTO> getAllRatings();
   List<RatingDTO> getRatingByUserId(String userId);
   List<RatingDTO> getRatingByHotelId(String hotelId);
}
