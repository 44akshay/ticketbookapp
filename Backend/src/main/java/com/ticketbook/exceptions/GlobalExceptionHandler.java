package com.ticketbook.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ticketbook.payloads.ApiResponse;


@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(NoSeatAvailableException.class)
	public ResponseEntity<Map<String,String>> seatsNotAvailableExceptionHandleer(NoSeatAvailableException ex){
		String message=ex.getMessage();
		Map<String,String> resp=new HashMap<>();
		resp.put("message", message);
		resp.put("Booking", "false");
		return new ResponseEntity<Map<String,String>>(resp,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleMethodnotValidExc(MethodArgumentNotValidException ex){
		Map<String,String> resp=new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((errors)->{
			String field = ((FieldError)errors).getField();
			String defaultMessage = errors.getDefaultMessage();
			resp.put(field, defaultMessage);
		});;
		
		return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
	}

}
