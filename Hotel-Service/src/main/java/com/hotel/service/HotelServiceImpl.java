package com.hotel.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.dto.HotelDTO;
import com.hotel.entity.Hotel;
import com.hotel.exceptions.ResourceNotFoundException;
import com.hotel.repository.HotelRepo;

@Service
public class HotelServiceImpl implements HotelService
{
	@Autowired
	private HotelRepo hotelRepo;

	@Autowired
	private ModelMapper mapper;
	
	@Override
	public HotelDTO createHotel(HotelDTO hotelDTO) 
	{
		String 	hotelId 	= 	UUID.randomUUID().toString();
		Hotel 	hotel 	=	this.mapper.map(hotelDTO, Hotel.class);
		
		hotel.setId(hotelId);
		
		Hotel createdHotel = this.hotelRepo.save(hotel);
		
		return this.mapper.map(createdHotel, HotelDTO.class);
	}

	@Override
	public HotelDTO getHotel(String hotelId) 
	{
		Hotel hotel = this.hotelRepo.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("Hotel with id " + hotelId + " is not found"));
		
		return this.mapper.map(hotel, HotelDTO.class);
	}

	@Override
	public List<HotelDTO> getAllHotels()
	{
		List<Hotel> 	allHotels 		= 	 this.hotelRepo.findAll();
		List<HotelDTO>	hotelDTOList	=	new ArrayList();
		
		allHotels.stream().forEach(hotel -> {
			hotelDTOList.add(this.mapper.map(hotel, HotelDTO.class));
		});
			
		return hotelDTOList;
	}
}
