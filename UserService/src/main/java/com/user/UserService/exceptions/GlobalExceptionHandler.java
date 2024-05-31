package com.user.UserService.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.user.UserService.payload.ApiResponse;


@RestControllerAdvice //handling exception globally
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundException ex){
		
		String message=ex.getMessage();
		//ApiResponse responce=ApiResponse.builder().message(message).success(true).status(HttpStatus.NOT_FOUND).build();
	                return new ResponseEntity<ApiResponse>(HttpStatus.NOT_FOUND);
	}
}
