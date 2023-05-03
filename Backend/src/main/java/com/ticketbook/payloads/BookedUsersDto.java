package com.ticketbook.payloads;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookedUsersDto {
	private String userName;
	private String userPhone;
	private List<String> seatNums;

}
