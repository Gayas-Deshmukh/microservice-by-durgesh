package com.user.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.dto.Hotel;
import com.user.dto.Rating;
import com.user.dto.UserDTO;
import com.user.entity.User;
import com.user.exceptions.ResourceNotFoundException;
import com.user.external.services.HotelService;
import com.user.external.services.RatingService;
import com.user.repository.UserRepo;

@Service
public class UserServiceImp implements UserService
{
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private HotelService hotelService;
	
	@Autowired
	private RatingService ratingService;
	
	@Override
	public UserDTO createUser(UserDTO userDTO) 
	{	
		String 	userId 	= 	UUID.randomUUID().toString();
		User 	user 	=	this.mapper.map(userDTO, User.class);
		
		user.setId(userId);
		
		User createduser = this.userRepo.save(user);
		
		return this.mapper.map(createduser, UserDTO.class);
	}

	@Override
	public UserDTO getUser(String userId) 
	{
		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with id " + userId + " is not found"));
		
		// Once you get the user, call Rating Service to get its ratings & for that you use can RestTemplate
		// http://localhost:8083/rating/user/3134e578-6978-47a4-b5f3-c8282455adf9
		
		//Rating [] ratings = this.restTemplate.getForObject("http://localhost:8083/rating/user/" + userId, Rating [].class);
		
		// Using Service Name registered with Service Registry
		//Rating [] ratings = this.restTemplate.getForObject("http://RATING-SERVICE/rating/user/" + userId, Rating [].class);

		// Using FeingClient
		Rating [] ratings = this.ratingService.getRating(userId);

		Arrays.asList(ratings).stream().forEach(rating -> {
			// Call Hotel Service to get Hotel Info
			// http://localhost:8082/hotels/d6e8b0e7-264e-4a0e-b4d4-dd265ce7b8f7
			//Hotel hotel = this.restTemplate.getForObject("http://localhost:8082/hotels/" + rating.getHotelId(), Hotel.class);

			// Using Service Name registered with Service Registry
			//Hotel hotel = this.restTemplate.getForObject("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);

			// Using FeingClient
			Hotel hotel = this.hotelService.getHotel(rating.getHotelId());
			
			// Set Hotel info on Rating
			rating.setHotel(hotel);
		});
		
		UserDTO userDTO = this.mapper.map(user, UserDTO.class);
		
		userDTO.setRating(Arrays.asList(ratings));
		
		return userDTO;
	}

	@Override
	public List<UserDTO> getAllUsers() 
	{
		List<User> 		allUsers 	= this.userRepo.findAll();
		List<UserDTO>	userDTOList	= new ArrayList();
		
		allUsers.stream().forEach(user -> {
			userDTOList.add(this.mapper.map(user, UserDTO.class));
		});
			
		return userDTOList;
	}
}
