package com.ticketbook.services;

import java.util.List;

import com.ticketbook.payloads.BookedUsersDto;
import com.ticketbook.payloads.SeatNumberDto;

public interface SeatNumberServices {
	
	//resets all the seats
	public void Reset();
	
	//send all the seat booking status
	public List<SeatNumberDto> getAllSeatStatus();
	
	public List<BookedUsersDto> getAllBookedUser();
	

}
