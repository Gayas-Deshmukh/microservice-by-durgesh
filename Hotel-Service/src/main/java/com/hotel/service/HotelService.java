package com.hotel.service;

import java.util.List;

import com.hotel.dto.HotelDTO;

public interface HotelService 
{
	HotelDTO createHotel(HotelDTO hotelDTO);

	HotelDTO getHotel(String hotelId);

	List<HotelDTO> getAllHotels();
}
