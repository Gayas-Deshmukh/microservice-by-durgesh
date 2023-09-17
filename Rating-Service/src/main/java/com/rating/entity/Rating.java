package com.rating.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rating")
public class Rating
{
	@Id
	@Column(name = "id")
	private String ratingId;
	
	@Column(name = "rating")
	private int rating;
	
	@Column(name = "feedback")
	private String feedback;
	
	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "hotel_id")
	private String hotelId;
}
