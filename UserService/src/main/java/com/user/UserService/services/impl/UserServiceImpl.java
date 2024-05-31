package com.user.UserService.services.impl;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.UserService.entities.Rating;
import com.user.UserService.entities.User;
import com.user.UserService.entities.Hotel;
import com.user.UserService.exceptions.ResourceNotFoundException;
import com.user.UserService.external.services.HotelService;
import com.user.UserService.repository.UserRepository;
import com.user.UserService.services.UserService;


@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	//for communication with RESTful web services
	@Autowired
	private RestTemplate restTemplate;
	
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);	
	
    @Autowired
    private HotelService hotelService;
    
    
	@Override
	public User saveUser(User user) {
		//generating real time unique Id
		String ramdomUserId=UUID.randomUUID().toString();
		user.setUserId(ramdomUserId);
		
	    return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		
		List<User> users=userRepository.findAll();
		Iterator<User> iterator = users.iterator();
		while (iterator.hasNext()) {
		    User user = iterator.next();
			//ArrayList<Rating> ratingsOfUser=restTemplate.getForObject("http://localhost:8083/ratings/users/"+user.getUserId(),ArrayList.class);
			ArrayList<Rating> ratingsOfUser=restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(),ArrayList.class);
			user.setRatings(ratingsOfUser); 
		}

		 return users;
	}

	@Override
	public User getUser(String userId) {
		//get user from db with the help of user repository 
		User user=userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with given id ="+userId+" is not found on server"));
	 
		//1.setting userWise ratings
		//we dont have rating according to userId hence call ratingsERVICE API   
	    //http://localhost:8083/ratings/hotels/<userId>
	   //Rating[] ratingsOfUser=restTemplate.getForObject("http://localhost:8083/ratings/users/"+user.getUserId(),Rating[].class);	        
	Rating[] ratingsOfUser=restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(),Rating[].class);	        
	logger.info("{}",ratingsOfUser);
            List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();
            
            
            
            
            
          //2.attching ratings given to which hotel with hotelservice api
    
           List<Rating> ratingList = ratings.stream().map(rating -> {
           	// ResponseEntity<Hotel> entityObject=restTemplate.getForEntity("http://localhost:8082/hotels/"+rating.getHotelId(), Hotel.class);
            //ResponseEntity<Hotel> entityObject=restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
    	    // Hotel hotel=entityObject.getBody();
    	
            //  we have resistered HOTEL-SERVICE as feignClient and thus we can avoid use of above restTemplate
       	      Hotel hotel=hotelService.getHotel(rating.getHotelId());

        	   
        	   rating.setHotel(hotel);
    	 return rating;
     }).collect(Collectors.toList());      
            
            user.setRatings(ratingList);
	        return user;
	}

}
