package com.user.controller;

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

import com.user.dto.UserDTO;
import com.user.payload.ApiResponse;
import com.user.service.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/users")
public class UserController 
{
	@Autowired
	private UserService userService;
	
	int retrycount = 1;

	@PostMapping()
	public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO)
	{
		 UserDTO user = this.userService.createUser(userDTO);
		 
		 return new ResponseEntity<UserDTO>(user, HttpStatus.CREATED);
	}
	
	@GetMapping("/{userId}")
	//@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
	//@Retry(name ="ratingHotelRetry",fallbackMethod = "ratingHotelFallback")
	@RateLimiter(name = "ratingHotelRateLimiter", fallbackMethod = "ratingHotelRateLimiterFallback" )
	// RateLimiter is used to restrict the user to send multiple requests.i.e it is use to control number of requests
	public ResponseEntity<UserDTO> getUser(@PathVariable("userId") String userId)
	{
		System.out.println("RetryCount : " + retrycount);
		
		retrycount++;
		
		 UserDTO user = this.userService.getUser(userId);
		 
		 return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
	}
	
	@GetMapping()
	public ResponseEntity<List<UserDTO>> getAllUser()
	{
		  List<UserDTO> allUsers = this.userService.getAllUsers();
		 
		 return new ResponseEntity<List<UserDTO>>(allUsers, HttpStatus.OK);
	}
	
	// fallback method
	public ResponseEntity<ApiResponse> ratingHotelFallback(String userId, Exception ex)
	{
		ApiResponse apiResponse = new ApiResponse();
		
		apiResponse.setMessage("Hotel or Rating service is down");
		apiResponse.setSuccess(true);
		apiResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		 
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
	}
	
	// RateLimiter method
	public ResponseEntity<ApiResponse> ratingHotelRateLimiterFallback(String userId, Exception ex)
	{
		ApiResponse apiResponse = new ApiResponse();
		
		apiResponse.setMessage("Server Down due to multiple request, Please try after some time");
		apiResponse.setSuccess(true);
		apiResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		 
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
	}
	
}
