package com.ssafy.safefood.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.safefood.dto.Food;
import com.ssafy.safefood.repo.FoodRepository;

@Service
public class FoodServiceImpl implements FoodService{
	@Autowired
	FoodRepository repo;

	@Override
	public int getFoodsPageSize(int condition, String text) {
		return repo.getFoodsPageSize(condition, text);
	}

	@Override
	public List<Food> listFoods(int page, int condition, String text) {
		return repo.listFoods(page, condition, text);
	}

	@Override
	public List<Food> listRelativeFoods(int code) {
		Food nowFood = repo.getFoodByCode(code);
		List<Food> relativeFoodList = repo.listRelativeFoods(nowFood);
		relativeFoodList.sort((o1,o2)->Double.valueOf(getEuclidean(o1, nowFood)).compareTo(getEuclidean(o2, nowFood)));
		return relativeFoodList.subList(1, 5);
	}
	
	public double getEuclidean(Food now, Food comp) {
		double carbo = Math.pow(now.getCarbo() - comp.getCarbo(), 2);
		double protein = Math.pow(now.getProtein() - comp.getProtein(), 2);
		double fat = Math.pow(now.getFat() - comp.getFat(), 2);
		double sugar = Math.pow(now.getSugar() - comp.getSugar(), 2);
		double natrium = Math.pow(now.getNatrium() - comp.getNatrium(), 2);
		double chole = Math.pow(now.getChole() - comp.getChole(), 2);
		double fattyacid = Math.pow(now.getFattyacid() - comp.getFattyacid(), 2);
		double transfat = Math.pow(now.getTransfat() - comp.getTransfat(), 2);
		
		return Math.sqrt(carbo+protein+fat+sugar+natrium+chole+fattyacid+transfat);
	}

}
