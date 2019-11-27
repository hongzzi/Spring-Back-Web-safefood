package com.ssafy.safefood.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class Menu {
	private int menuCode;
	private String menuName;
	private Date menuDate;
	private int menuOrder;
	private double carbo;
	private double protein;
	private double fat;
	private double sugar;
	private double natrium;
	private double chole;
	private double fattyacid;
	private double transfat;
	
}
