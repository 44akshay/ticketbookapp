package com.ticketbook.payloads;

import com.ticketbook.entities.Users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SeatNumberDto {
	
	private int seatId;
	private String seatName;
	private Boolean isBooked;
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getSeatName()+":"+this.getIsBooked();
	}
	 
}
