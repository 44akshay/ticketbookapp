package com.ticketbook.servicesimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticketbook.entities.SeatNumbers;
import com.ticketbook.entities.Users;
import com.ticketbook.payloads.BookedUsersDto;
import com.ticketbook.payloads.SeatNumberDto;
import com.ticketbook.repositories.SeatNumberRepo;
import com.ticketbook.repositories.UserRepo;
import com.ticketbook.services.SeatNumberServices;

@Service
public class SeatNumberServiceImpl implements SeatNumberServices {

	@Autowired
	private SeatNumberRepo seatNumberRepo;
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	//reset the seat number to not booked 
	@Override
	public void Reset() {
		// inserting all the 80 seats based on giving names to rows
		this.seatNumberRepo.deleteAllSeats();
		 int seatchar =64;
		 int index=0;
		 for(int i=0;i<80;i++){
			 if(i%7==0) {
				 seatchar++;
				 index=0;
			 }
			 index++; 
			 SeatNumbers seatNumbers=new SeatNumbers();
			 seatNumbers.setSeatId(i); 
			 seatNumbers.setSeatName((char)seatchar+""+index);
			 seatNumbers.setIsBooked(false);
			 this.seatNumberRepo.save(seatNumbers); }
		 

	}
	//This will be called for getting all the seat booking status
	@Override
	public List<SeatNumberDto> getAllSeatStatus() {
		List<SeatNumbers> seatnums = this.seatNumberRepo.findAll();
		List<SeatNumberDto> seatnumsdto = seatnums.stream().map((seat)->this.modelMapper.map(seat, SeatNumberDto.class)).collect(Collectors.toList());
		return seatnumsdto;
	}

	//This will be called to get the booked users along with their seat nums
	//Groupby is being done on user and their users are fetched
	@Override
	public List<BookedUsersDto> getAllBookedUser() {
		List<BookedUsersDto> bookedusers=new ArrayList<>();
		List<Object[]> getbookeduserseats = this.seatNumberRepo.getbookeduserseats();
		getbookeduserseats.forEach((item)->{
			BookedUsersDto bookedUsersDto=new BookedUsersDto();
			if(item[0]!=null) {
				bookedUsersDto.setUserPhone(item[0].toString());
				List<String> seats=new ArrayList<>();
				Users userinfo = this.userRepo.findByuserPhoneNumber(item[0].toString());
				bookedUsersDto.setUserName(userinfo.getUserName());
				List<String> seatnums = Stream.of(item[1].toString().split(",")).collect(Collectors.toList());
				bookedUsersDto.setSeatNums(seatnums);
				bookedusers.add(bookedUsersDto);
			}
			
			
		});
		return bookedusers;
	}

}
