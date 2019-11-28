package com.ssafy.safefood.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Intake {
	private int intakeId;
	private int foodId;
	private String email;
	private int quantity;
	private Date intakeDate;
	
	public Intake(int foodId, String email, int quantity, Date intakeDate) {
		super();
		this.foodId = foodId;
		this.email = email;
		this.quantity = quantity;
		this.intakeDate = intakeDate;
	}
	
}
