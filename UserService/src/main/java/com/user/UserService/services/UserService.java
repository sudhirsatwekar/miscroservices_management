package com.user.UserService.services;

import java.util.List;
import com.user.UserService.entities.User;

public interface UserService  {
	
	//define USer Operations
	
	//create
	User saveUser(User user);
	
	//get all users
	List<User> getAllUser();
	
	//get single user of given userId
	User getUser(String userId);
	
	//TODO:delete and Update
	

}
