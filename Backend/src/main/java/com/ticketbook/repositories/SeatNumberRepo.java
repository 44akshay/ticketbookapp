package com.ticketbook.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ticketbook.entities.SeatNumbers;

import jakarta.transaction.Transactional;

public interface SeatNumberRepo extends JpaRepository<SeatNumbers, Integer> {

	@Modifying
	@Transactional
	@Query(value = "delete from seatnumbers",nativeQuery = true)
	public void deleteAllSeats();
	
	@Query(value = "select user_userphonenum,group_concat(seatname) from ticketbookapp.seatnumbers group by user_userphonenum",nativeQuery = true)
	List<Object[]> getbookeduserseats();
}
