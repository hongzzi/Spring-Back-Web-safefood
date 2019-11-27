package com.ssafy.safefood.repo;

import java.util.List;

import com.ssafy.safefood.dto.Food;

public interface FoodRepository {
	
	int getFoodsPageSize(int condition, String text);
	
	List<Food> listFoods(int page, int condition, String text);

	Food getFoodByCode(int code);

	List<Food> listRelativeFoods(Food nowFood);
	
}
