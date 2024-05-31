package com.rating.RatingService.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.rating.RatingService.entities.Rating;
//for mongoDB
public interface RatingRepository extends MongoRepository<Rating, String>  {

	
	//custom finder methods because above MongoRepository not gives by default we have to create them
	
	//getAllRatingsByUserId
	List<Rating> findByUserId(String userId);
	
	
	////getAllRatingsByHotelId
	List<Rating> findByHotelId(String hotelId);
	
	
	
}
