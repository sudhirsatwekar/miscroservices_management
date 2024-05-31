package com.user.UserService.exceptions;

public class ResourceNotFoundException extends RuntimeException{

	//parameterless constructor
	public ResourceNotFoundException() {
		
		super("Resource not found on server");
	}
	
	//parameterized constructor
    public ResourceNotFoundException(String message) {
		
		super(message);
	}
}
