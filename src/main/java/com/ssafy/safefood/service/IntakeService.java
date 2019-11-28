package com.ssafy.safefood.service;

import java.util.Date;
import java.util.List;

import com.ssafy.safefood.dto.Intake;

public interface IntakeService {
	
	List<Intake> getHistoryList(String email, Date intakeDate);
	
	int addHistory(Intake intake);
	int editHistory(Intake intake);
	int removeHistory(Intake intake);
}
