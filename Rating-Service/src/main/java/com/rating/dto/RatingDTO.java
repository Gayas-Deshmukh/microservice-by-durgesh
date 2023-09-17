package com.rating.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RatingDTO 
{
	private String ratingId;
	private int rating;
	private String feedback;
	private String userId;
	private String hotelId;
}
