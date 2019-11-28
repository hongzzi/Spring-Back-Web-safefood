package com.ssafy.safefood.service;

import java.sql.Date;
import java.util.List;

import com.ssafy.safefood.dto.Intake;

public interface IntakeService {
	
	List<Intake> getHistoryList(String email, Date intakeDate);
	
	int addHistory(Intake intake);
	int editHistory(Intake intake);
	int removeHistory(int intakeId);
}
