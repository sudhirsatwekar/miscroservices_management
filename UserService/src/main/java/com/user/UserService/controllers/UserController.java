package com.user.UserService.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.UserService.entities.User;
import com.user.UserService.services.UserService;
import com.user.UserService.services.impl.UserServiceImpl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/users")
public class UserController {
	
    @Autowired
    private UserService userService;
    
    private Logger logger = LoggerFactory.getLogger(UserController.class);	

	
	//create
    @PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user){
		User user1=userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user1);
		
	}
    int retryCount=1;
	
	//single user get
	@GetMapping("/{userId}")
	//1.@CircuitBreaker(name="ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")
	//2.@Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallback")
	 @RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingHotelFallback")
	public ResponseEntity<User> getSingleUser(@PathVariable String userId){
		logger.info("Retry count:{}",retryCount);
		retryCount++;
		User user=userService.getUser(userId);
		return ResponseEntity.ok(user);
	}
	
	
	//creating fallback method for circuitbreaker if above method fails this will get execute
	public ResponseEntity<User> ratingHotelFallback(String userId,Exception ex){
		logger.info("Fallback is executed because service is down: ",ex.getMessage() );
		
		User user=new User();
		user.setName("Dummy");
		user.setEmail("dummy@gmmail.com");
		user.setAbout("This dummy user is created because some service is down");
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	
	//all user get
	@GetMapping
	public ResponseEntity<List<User>> getAllUser(){
	   List<User> allUser=userService.getAllUser();
	   return ResponseEntity.ok(allUser);
	}
	
	
	
}

