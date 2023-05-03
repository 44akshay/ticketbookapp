package com.ticketbook.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "seatnumbers")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class SeatNumbers {
	@Id
	@Column(name = "seatid")
	private int seatId;
	@Column(name="seatname")
	private String seatName;
	@Column(name = "isbooked")
	private Boolean isBooked;
	@ManyToOne
	private Users user;
	
	@Override
	public String toString() {
		return this.getSeatName()+":"+this.getIsBooked();
	}
	
	//userid

}
