package com.ticketbook.services;

import com.ticketbook.payloads.UserDto;

public interface UserServices {
	
	//Removing all the users from the database
	public void deleteAllUser();
	
	//registering a user
	public void registerUser(UserDto userDto);
	
	//user requesting for seat
	public UserDto bookSeat(UserDto userDto);

}
