package com.rating.RatingService.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rating.RatingService.entities.Rating;

@Service
public interface RatingService {

	
	//create
	Rating createRating(Rating rating);
	
	//getAllRatings
	List<Rating> getRatings();
	
	//getAllByUserId
	List<Rating> getAllRatingByUserId(String userId);
	
	//getAllByHotelId
	List<Rating> getAllRatingByHotelId(String hotelId);

}
