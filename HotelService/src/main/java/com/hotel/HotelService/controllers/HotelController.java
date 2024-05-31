package com.hotel.HotelService.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.HotelService.entities.Hotel;
import com.hotel.HotelService.services.HotelService;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    @Autowired
	private HotelService hotelservice;
	
	@PostMapping
	public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
		String hotelId=UUID.randomUUID().toString();
		hotel.setId(hotelId);
		return ResponseEntity.status(HttpStatus.CREATED).body(hotelservice.create(hotel));
	}
	
	@GetMapping("/{hotelId}")
	public ResponseEntity<Hotel> getSingleHotel(@PathVariable String hotelId){
		return ResponseEntity.status(HttpStatus.OK).body(hotelservice.get(hotelId));
	}
	
	@GetMapping
	public ResponseEntity<List<Hotel>> getAll(){
		return ResponseEntity.ok(hotelservice.getall());
	}
	
	
	
}
