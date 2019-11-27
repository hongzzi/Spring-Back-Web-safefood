package com.ssafy.safefood.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.safefood.dto.Food;
import com.ssafy.safefood.repo.MenuRepository;

@Service
public class MenuServiceImpl implements MenuService{
	
	@Autowired
	MenuRepository repo;
	
	@Override
	public List<Food> getWeekdayMenuList(Date startDate, Date endDate) {
		return repo.getWeekdayMenuList(startDate, endDate);
	}

}
