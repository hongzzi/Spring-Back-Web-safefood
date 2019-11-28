package com.ssafy.safefood.repo;

import java.sql.Date;
import java.util.List;

import com.ssafy.safefood.dto.Intake;

public interface IntakeRepository {

	List<Intake> selectHistory(String email, Date intakeDate);
	
	int insertHistory(Intake intake);
	int updateHistory(Intake intake);
	int deleteHistory(int intakeId);
}
