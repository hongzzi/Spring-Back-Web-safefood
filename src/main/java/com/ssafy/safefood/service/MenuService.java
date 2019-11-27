package com.ssafy.safefood.service;

import java.sql.Date;
import java.util.List;

import com.ssafy.safefood.dto.Food;

public interface MenuService {
	List<Food> getWeekdayMenuList(Date startDate, Date endDate);
}
