package com.hotel.HotelService.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.HotelService.entities.Hotel;
import com.hotel.HotelService.exceptions.ResourceNotFoundException;
import com.hotel.HotelService.repositories.HotelRepository;
import com.hotel.HotelService.services.HotelService;


@Service
public class HotelServiceImpl implements HotelService {
  
	@Autowired
	private HotelRepository hotelRepository;
	
	@Override
	public Hotel create(Hotel hotel) {
		return hotelRepository.save(hotel);
	}

	@Override
	public List<Hotel> getall() {
		return hotelRepository.findAll();
	}

	@Override
	public Hotel get(String id) {
		return hotelRepository.findById(id).
				orElseThrow(()->new ResourceNotFoundException("Hotel not found with given Id"));
	}

}