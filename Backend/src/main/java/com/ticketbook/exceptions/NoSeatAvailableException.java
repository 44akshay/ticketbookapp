package com.ticketbook.exceptions;

public class NoSeatAvailableException extends RuntimeException {
	public NoSeatAvailableException() {
		super("No Seats are Available as per request");
	}

}
