package com.ssafy.safefood.repo;

import java.sql.Date;
import java.util.List;

import com.ssafy.safefood.dto.Food;

public interface MenuRepository {

	List<Food> getWeekdayMenuList(Date startDate, Date endDate);
	
}
