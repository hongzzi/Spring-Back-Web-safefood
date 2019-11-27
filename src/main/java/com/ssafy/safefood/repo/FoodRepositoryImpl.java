package com.ssafy.safefood.repo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.safefood.dto.Food;

@Repository
public class FoodRepositoryImpl implements FoodRepository{
	private final String MAPPER_STATEMENT = "sql.foodMapper.";
	private final int PAGE_SIZE = 6;
	
	@Autowired
	SqlSession session;
	
	@Override
	public int getFoodsPageSize(int condition, String text) {
		Map<String, Object> params = new HashMap<>();
		params.put("condition", condition);
		params.put("text", "%"+text+"%");
		return session.selectOne(MAPPER_STATEMENT+"getFoodsPageSize",params); 
	}

	@Override
	public List<Food> listFoods(int page, int condition, String text) {
		Map<String, Object> params = new HashMap<>();
		params.put("startPage", (page-1)*PAGE_SIZE);
		params.put("sizePage", PAGE_SIZE);
		params.put("condition", condition);
		params.put("text", "%"+text+"%");
		return session.selectList(MAPPER_STATEMENT+"searchFoods", params);
	}

	@Override
	public Food getFoodByCode(int code) {
		return session.selectOne(MAPPER_STATEMENT+"getFoodByCode", code);
	}

	@Override
	public List<Food> listRelativeFoods(Food nowFood) {
		return session.selectList(MAPPER_STATEMENT+"listRelativeFoods", nowFood);
	}

}
