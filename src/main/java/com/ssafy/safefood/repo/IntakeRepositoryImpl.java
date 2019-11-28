package com.ssafy.safefood.repo;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.safefood.dto.Intake;

@Repository
public class IntakeRepositoryImpl implements IntakeRepository{

	private final String namespace = "sql.intakeMapper.";
	
	@Autowired
	SqlSession session;
	
	@Override
	public List<Intake> selectHistory(String email, Date intakeDate) {
		Map<String, Object> map = new HashMap<>();
		map.put("email", email);
		map.put("intakeDate", intakeDate);
		return session.selectList(namespace+"restIntakeSelectByDate", map);
	}

	@Override
	public int insertHistory(Intake intake) {
		return session.insert(namespace+"restIntakeInsert", intake);
	}

	@Override
	public int updateHistory(Intake intake) {
		return session.update(namespace+"restIntakeUpdate", intake);
	}

	@Override
	public int deleteHistory(Intake intake) {
		return session.delete(namespace+"restIntakeDelete", intake);
	}

}
