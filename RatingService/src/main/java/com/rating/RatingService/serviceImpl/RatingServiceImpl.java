package com.rating.RatingService.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rating.RatingService.entities.Rating;
import com.rating.RatingService.repository.RatingRepository;
import com.rating.RatingService.services.RatingService;


@Service
public class RatingServiceImpl implements RatingService {

	
	@Autowired
	private RatingRepository repository;
	
	
	
	@Override
	public Rating createRating(Rating rating) {
		return repository.save(rating);
	}

	@Override
	public List<Rating> getRatings() {
		return repository.findAll();
	}
   
	@Override
	public List<Rating> getAllRatingByUserId(String userId) {
		//custom method
		return repository.findByUserId(userId);
	}

	@Override
	public List<Rating> getAllRatingByHotelId(String hotelId) {
		//custom method
		return repository.findByHotelId(hotelId);
	}

}
