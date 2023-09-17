package com.rating.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rating.dto.RatingDTO;
import com.rating.entity.Rating;

public interface RatingRepo extends JpaRepository<Rating, String>
{
   List<Rating> findByUserId(String userId);
   List<Rating> findByHotelId(String hotelId);
}
