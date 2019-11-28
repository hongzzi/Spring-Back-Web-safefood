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
	private String foodName;
	private double calory;
	private double carbo;
	private double protein;
	private double fat;
}
