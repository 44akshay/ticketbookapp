package com.ticketbook.payloads;

import java.util.List;

import org.hibernate.validator.constraints.Range;

import com.ticketbook.entities.SeatNumbers;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	@Size(min=10,max = 10, message = "Please provide a valid phone number")
	private String phoneNumber;
	@Size(min=3, message = "user name should be more than 3 character")
	private String userName;
	private int noOfSeats;
	private List<SeatNumberDto> seatNumbers;
}
