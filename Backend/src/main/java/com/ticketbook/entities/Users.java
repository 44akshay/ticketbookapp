package com.ticketbook.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Users {
	@Id
	@Column(name = "userphonenum")
	private String userPhoneNumber;
	@Column(name = "username")
	private String userName;
	@Column(name = "no_of_seat")
	private int noOfSeats;
	@OneToMany(mappedBy ="user", cascade = CascadeType.ALL)
	private List<SeatNumbers> numbers=new ArrayList<>();
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getUserName()+""+this.getUserPhoneNumber();
	}
	
}
