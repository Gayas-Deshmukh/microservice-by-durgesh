package com.hotel.controller;

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

import com.hotel.dto.HotelDTO;
import com.hotel.service.HotelService;

@RestController
@RequestMapping("/hotels")
public class HotelController 
{
	@Autowired
     private HotelService hotelService;
	
	@PostMapping()
	public ResponseEntity<HotelDTO> createHotel(@RequestBody HotelDTO hotelDTO)
	{
		 HotelDTO hotel = this.hotelService.createHotel(hotelDTO);
		 
		 return new ResponseEntity<HotelDTO>(hotel, HttpStatus.CREATED);
	}
	
	@GetMapping("/{hotelId}")
	public ResponseEntity<HotelDTO> getHotel(@PathVariable("hotelId") String hotelId)
	{
		HotelDTO hotel = this.hotelService.getHotel(hotelId);
		 
		 return new ResponseEntity<HotelDTO>(hotel, HttpStatus.OK);
	}
	
	@GetMapping()
	public ResponseEntity<List<HotelDTO>> getAllHotels()
	{
		  List<HotelDTO> allHotels = this.hotelService.getAllHotels();
		 
		 return new ResponseEntity<List<HotelDTO>>(allHotels, HttpStatus.OK);
	}
}
