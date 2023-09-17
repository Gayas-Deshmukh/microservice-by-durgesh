package com.rating.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rating.dto.RatingDTO;
import com.rating.service.RatingService;

@RestController
@RequestMapping("/rating")
public class RatingController 
{
	@Autowired
	private RatingService ratingService;
	
	@PostMapping()
	public ResponseEntity<RatingDTO> addRating(@RequestBody RatingDTO ratingDTO)
	{
		RatingDTO rating = this.ratingService.addRating(ratingDTO);
		 
		 return new ResponseEntity<RatingDTO>(rating, HttpStatus.CREATED);
	}
	
	@GetMapping()
	public ResponseEntity<List<RatingDTO>> getAllRatings()
	{
		  List<RatingDTO> allRating = this.ratingService.getAllRatings();
		 
		 return new ResponseEntity<List<RatingDTO>>(allRating, HttpStatus.OK);
	}
	
	@GetMapping("/hotel/{hotelId}")
	public ResponseEntity<List<RatingDTO>> getRatingsByHotelId(@PathVariable String hotelId)
	{
		  List<RatingDTO> allRating = this.ratingService.getRatingByHotelId(hotelId);
		 
		 return new ResponseEntity<List<RatingDTO>>(allRating, HttpStatus.OK);
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<RatingDTO>> getRatingsByUserId(@PathVariable String userId)
	{
		  List<RatingDTO> allRating = this.ratingService.getRatingByUserId(userId);
		 
		 return new ResponseEntity<List<RatingDTO>>(allRating, HttpStatus.OK);
	}
}
