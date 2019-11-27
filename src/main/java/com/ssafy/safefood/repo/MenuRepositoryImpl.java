package com.ssafy.safefood.repo;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.safefood.dto.Food;

@Repository
public class MenuRepositoryImpl implements MenuRepository{
	private final String MAPPER_STATEMENT = "sql.menuMapper.";
	@Autowired
	SqlSession session;
	@Override
	public List<Food> getWeekdayMenuList(Date startDate, Date endDate) {
		Map<String, Object> params = new HashMap<>();
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		return session.selectList(MAPPER_STATEMENT+"getWeekdayMenuList", params);
	}

}
