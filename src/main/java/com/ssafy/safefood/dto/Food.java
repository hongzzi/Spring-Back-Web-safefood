package com.ssafy.safefood.dto;

import lombok.Data;

@Data
public class Food {
	private int code;
	private String name;
	private double supportpereat;
	private double calory;
	private double carbo;
	private double protein;
	private double fat;
	private double sugar;
	private double natrium;
	private double chole;
	private double fattyacid;
	private double transfat;
	private String maker;
	private String material;
	private String img;
	private String allergy;
}
