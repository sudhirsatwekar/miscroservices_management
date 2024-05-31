package com.hotel.HotelService.services;

import java.util.List;

import com.hotel.HotelService.entities.Hotel;



public interface HotelService {

	//create 
	 Hotel create(Hotel hotel);
	
	//getall
	List<Hotel> getall();
	
	//get single
	Hotel get(String id);
	
	
}
