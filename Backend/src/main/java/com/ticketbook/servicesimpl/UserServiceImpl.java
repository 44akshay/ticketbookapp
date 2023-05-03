package com.ticketbook.servicesimpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticketbook.entities.SeatNumbers;
import com.ticketbook.entities.Users;
import com.ticketbook.exceptions.NoSeatAvailableException;
import com.ticketbook.payloads.SeatNumberDto;
import com.ticketbook.payloads.UserDto;
import com.ticketbook.repositories.SeatNumberRepo;
import com.ticketbook.repositories.UserRepo;
import com.ticketbook.services.UserServices;

@Service
public class UserServiceImpl implements UserServices {

	@Autowired
	private SeatNumberRepo seatNumberRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private ModelMapper mapper;
	private List<LinkedList<SeatNumberDto>> lists;
	private Users user;
	private int availableSeats[];
	int rqdSeats;
	boolean newUser=true;
	//This will be called along with reset to delete the user 
	@Override
	public void deleteAllUser() {
		this.userRepo.deleteAll();
		
	}
	//This is called to book the seats based on the requirement
	@Override
	public UserDto bookSeat(UserDto userDto) {
		
		rqdSeats=userDto.getNoOfSeats();
		availableSeats=new int[12];
		InitializingDataStrucure();
		//first priority to get seats in the same row
		int index=checkSeatsSameRow();
		if(index!=-1) {
			List<SeatNumberDto> bookticketdto = BookTicket(index);
			List<SeatNumbers> bookticket=new ArrayList<>();
			for(SeatNumberDto seat:bookticketdto) {
				SeatNumbers seatbyid = this.seatNumberRepo.findById(seat.getSeatId()).orElseThrow();
				seatbyid.setIsBooked(true);
				bookticket.add(seatbyid);
			}
			
			if(!newUser) {
				user.setUserName(userDto.getUserName());
				user.setNoOfSeats(user.getNoOfSeats()+userDto.getNoOfSeats());
				List<SeatNumbers> seat=user.getNumbers();
				for(SeatNumbers s:bookticket) {
					seat.add(s);
				}
				user.setNumbers(seat);
			}
			for(SeatNumbers s:bookticket) {
				s.setUser(user);
				this.seatNumberRepo.save(s);
			}
			UserDto dto=new UserDto();
			dto.setPhoneNumber(user.getUserPhoneNumber());
			dto.setUserName(user.getUserName());
			dto.setNoOfSeats(user.getNoOfSeats());
			if(!newUser) {
				List<SeatNumberDto> collect = user.getNumbers().stream().map((seat)->this.mapper.map(seat, SeatNumberDto.class)).collect(Collectors.toList());	
				dto.setSeatNumbers(collect);
				
			}else {
				dto.setSeatNumbers(bookticketdto);
			}
			
			return dto;
		}
		
		//if seats are not in a row, second priority to get seats in the nearby rows
		List<Integer> indexes = checkSeatsDiffRow();
		int startindex=indexes.get(0);
		int endindex=indexes.get(1);
		if(startindex!=-1 || endindex!=-1) {
			List<SeatNumberDto> bookticketdto=BookTicket(startindex,endindex);
			List<SeatNumbers> bookticket=new ArrayList<>();
			for(SeatNumberDto seat:bookticketdto) {
				SeatNumbers seatbyid = this.seatNumberRepo.findById(seat.getSeatId()).orElseThrow();
				seatbyid.setIsBooked(true);
				bookticket.add(seatbyid);
			}
			
			if(!newUser) {
				user.setUserName(userDto.getUserName());
				user.setNoOfSeats(user.getNoOfSeats()+userDto.getNoOfSeats());
				List<SeatNumbers> seat=user.getNumbers();
				for(SeatNumbers s:bookticket) {
					seat.add(s);
				}
				user.setNumbers(seat);
			}	
				
			for(SeatNumbers s:bookticket) {
				s.setUser(user);
				this.seatNumberRepo.save(s);
			}
			UserDto dto=new UserDto();
			dto.setPhoneNumber(user.getUserPhoneNumber());
			dto.setUserName(user.getUserName());
			dto.setNoOfSeats(user.getNoOfSeats());
			if(!newUser) {
				List<SeatNumberDto> collect = user.getNumbers().stream().map((seat)->this.mapper.map(seat, SeatNumberDto.class)).collect(Collectors.toList());	
				dto.setSeatNumbers(collect);
				
			}else {
				dto.setSeatNumbers(bookticketdto);
			}
			
			return dto;
		}
		//In case the seats are not available
		if(startindex==-1 || endindex==-1) {
			throw new NoSeatAvailableException();
		}
		
		return null;
	}

	
	
	//an array of linkedlist of seat and an array will be created in order to calculate and book the seat 
	public void InitializingDataStrucure() {
		List<SeatNumbers> seats = this.seatNumberRepo.findAll();
		List<SeatNumberDto> seatsdto = seats.stream().map((seat)->this.mapper.map(seat,SeatNumberDto.class))
		.collect(Collectors.toList());
		
		
		lists=new ArrayList<LinkedList<SeatNumberDto>>();
		for(int i=0;i<=11;i++) {
			lists.add(new LinkedList<>());
		}
		
		int index=-1;
		for(SeatNumberDto seat : seatsdto) {
			if(seat.getSeatId()%7==0) {
				index++;
			}
			lists.get(index).push(seat);
			if(!seat.getIsBooked()) {
				availableSeats[index]++;
			}
		}
	
	}
	
	//To check if seat number is in the same row or not 
	public int checkSeatsSameRow(){
		int index=-1;
		
		for(int i=0;i<12;i++) {
			if(rqdSeats<=availableSeats[i])
				return i;
		}
		
		return index;
	}
	
	//to book the row as nearby as possible
	public List<Integer> checkSeatsDiffRow(){
		System.out.println(rqdSeats+" is the asked seat");
		int startIndex=-1;
		int endIndex=-1;
		int rowdistance=Integer.MAX_VALUE;
		for(int i=0;i<12;i++) {
			int sum=availableSeats[i];
			for(int j=i+1;j<12;j++) {
				sum+=availableSeats[j];
				if(sum>=rqdSeats &&  (j-i)<rowdistance) {
					rowdistance=j-i;
					startIndex=i;
					endIndex=j;
					System.out.println("one I got starts with "+i+" and end with "+j);
					continue;
				}
			}
		}
		System.out.println(startIndex+"is start and end is "+endIndex);
		return List.of(startIndex,endIndex);
	}
	//This will traverse and book the required seat
	public List<SeatNumberDto> BookTicket(int index){
		List<SeatNumberDto> seats=new ArrayList<>();
		lists.get(index).forEach((temp)->{
			if(rqdSeats==0)return;
			if(!temp.getIsBooked()) {
				temp.setIsBooked(true);
				seats.add(temp);
				rqdSeats--;
			}
		});
		return seats;
	}
	//This will traverse and book the required seat
	public List<SeatNumberDto> BookTicket(int startindex,int endindex){
		System.out.println("reached here");
		List<SeatNumberDto> seats=new ArrayList<>();
		for(int i=startindex;i<=endindex;i++) {
			lists.get(i).forEach((temp)->{
				if(rqdSeats==0)return;
				if(!temp.getIsBooked()) {
					temp.setIsBooked(true);
					seats.add(temp);
					rqdSeats--;
				}
			});
		}
		System.out.println(startindex+" is s and e is "+endindex);
		return seats;
	}
	
	//This will register the user 
	@Override
	public void registerUser(UserDto userDto) {
		user = this.userRepo.findByuserPhoneNumber(userDto.getPhoneNumber());
		if(user==null){
			newUser=true;
			user=this.userRepo.save(this.mapper.map(userDto,Users.class));	
		}else {
			newUser=false;
		}
		
	}
}
