package com.rating.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.rating.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler 
{
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException ex)
	{
		ApiResponse apiResponse = ApiResponse.builder().message(ex.getMessage()).success(false).status(HttpStatus.NOT_FOUND).build();
	  
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
	}
}
