package com.user.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.user.dto.Rating;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService 
{
	@GetMapping("/rating/user/{usrId}")
    Rating [] getRating(@PathVariable("usrId") String userId);
}
