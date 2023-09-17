package com.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel.entity.Hotel;

public interface HotelRepo extends JpaRepository<Hotel, String>
{

}
