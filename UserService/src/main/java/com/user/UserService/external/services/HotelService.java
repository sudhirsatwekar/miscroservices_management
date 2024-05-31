
// Interface to resister [[[ FeignClient ]]] and use it in UserServiceImpl 
// we can use 

package com.user.UserService.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.user.UserService.entities.Hotel;
import com.user.UserService.entities.User;

@FeignClient(name="HOTEL-SERVICE")
public interface HotelService {
	
	//refer public User getUser(String userId) in UserServiceImpl
   @GetMapping("/hotels/{hotelId}")
	Hotel getHotel(@PathVariable("hotelId") String hotelId);
   
}
