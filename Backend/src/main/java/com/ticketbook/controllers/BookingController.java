package com.ticketbook.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ticketbook.payloads.ApiResponse;
import com.ticketbook.payloads.BookedUsersDto;
import com.ticketbook.payloads.SeatNumberDto;
import com.ticketbook.payloads.UserDto;
import com.ticketbook.repositories.UserRepo;
import com.ticketbook.services.SeatNumberServices;
import com.ticketbook.services.UserServices;

import jakarta.validation.Valid;
import lombok.val;

@RestController
@RequestMapping("/bookingapp/")
@CrossOrigin(origins ="*")
public class BookingController {
	@Autowired
	private SeatNumberServices seatNumberServices; 
	@Autowired
	private UserServices userServices;

	@GetMapping("/reset")
	public ResponseEntity<ApiResponse> Reset(){
		this.seatNumberServices.Reset();
		this.userServices.deleteAllUser();
		return new ResponseEntity<ApiResponse>(new ApiResponse("All Seats status is reset and Users are removed",true),HttpStatus.OK);
	}
	@PostMapping("/getseatnumber")
	public ResponseEntity<UserDto> getSeatNum(@Valid @RequestBody UserDto userDto){
		this.userServices.registerUser(userDto);
		//UserDto bookedUser=null;
		UserDto bookedUser = this.userServices.bookSeat(userDto);
		if(bookedUser==null) {
			return new ResponseEntity<UserDto>(bookedUser,HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<UserDto>(bookedUser,HttpStatus.OK);
		
	}
	
	@GetMapping("/getallseatstatus")
	public ResponseEntity<List<SeatNumberDto>> getAllSeats(){
		List<SeatNumberDto> allSeatStatus = this.seatNumberServices.getAllSeatStatus();
		
		return new ResponseEntity<List<SeatNumberDto>>(allSeatStatus,HttpStatus.OK);
	}
	@GetMapping("getallbookedusers")
	public ResponseEntity<List<BookedUsersDto>> getAllBookedUsers() throws InterruptedException{
		Thread.sleep(2000);
		List<BookedUsersDto> allBookedUser = this.seatNumberServices.getAllBookedUser();
		return new ResponseEntity<List<BookedUsersDto>>(allBookedUser,HttpStatus.OK);
	}
	
	
	
	
}
