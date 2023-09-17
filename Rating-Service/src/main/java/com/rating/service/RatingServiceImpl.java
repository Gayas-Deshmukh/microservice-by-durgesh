package com.rating.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rating.dto.RatingDTO;
import com.rating.entity.Rating;
import com.rating.repository.RatingRepo;

@Service
public class RatingServiceImpl implements RatingService
{
	@Autowired
	private RatingRepo ratingRepo;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public RatingDTO addRating(RatingDTO ratingDTO) 
	{
		String 	ratingId 	= 	UUID.randomUUID().toString();
		Rating 	rating 	   =	this.mapper.map(ratingDTO, Rating.class);
		
		rating.setRatingId(ratingId);
		
		Rating createdRating = this.ratingRepo.save(rating);
		
		return this.mapper.map(createdRating, RatingDTO.class);
	}

	@Override
	public List<RatingDTO> getAllRatings() 
	{
		List<Rating> 		allratings 		= this.ratingRepo.findAll();
		List<RatingDTO>		ratingDTOList	= new ArrayList();
		
		allratings.stream().forEach(rating -> {
			ratingDTOList.add(this.mapper.map(rating, RatingDTO.class));
		});
			
		return ratingDTOList;
	}

	@Override
	public List<RatingDTO> getRatingByUserId(String userId) 
	{
		List<Rating> 		ratings 	    = this.ratingRepo.findByUserId(userId);
		List<RatingDTO>		ratingDTOList	= new ArrayList();
		
		ratings.stream().forEach(rating -> {
			ratingDTOList.add(this.mapper.map(rating, RatingDTO.class));
		});
			
		return ratingDTOList;
	}

	@Override
	public List<RatingDTO> getRatingByHotelId(String hotelId)
	{
		List<Rating> 		ratings 	    = this.ratingRepo.findByHotelId(hotelId);
		List<RatingDTO>		ratingDTOList	= new ArrayList();
		
		ratings.stream().forEach(rating -> {
			ratingDTOList.add(this.mapper.map(rating, RatingDTO.class));
		});
			
		return ratingDTOList;
	}
	
	
}
