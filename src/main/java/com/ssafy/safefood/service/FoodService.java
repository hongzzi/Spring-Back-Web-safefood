package com.ssafy.safefood.service;

import java.util.List;

import com.ssafy.safefood.dto.Food;

public interface FoodService {

	int getFoodsPageSize(int condition, String text);

	List<Food> listFoods(int page, int condition, String text);

	List<Food> listRelativeFoods(int code);

}
