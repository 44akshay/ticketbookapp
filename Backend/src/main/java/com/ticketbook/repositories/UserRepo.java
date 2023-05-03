package com.ticketbook.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ticketbook.entities.Users;

public interface UserRepo extends JpaRepository<Users, String> {
	public Users findByuserPhoneNumber(String phonenumber);
}
