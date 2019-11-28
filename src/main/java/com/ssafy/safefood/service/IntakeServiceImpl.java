package com.ssafy.safefood.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.safefood.dto.Intake;
import com.ssafy.safefood.repo.IntakeRepository;

@Service
public class IntakeServiceImpl implements IntakeService {

	@Autowired
	IntakeRepository repo;
	
	@Override
	public List<Intake> getHistoryList(String email, Date intakeDate) {
		return repo.selectHistory(email, intakeDate);
	}

	@Override
	public int addHistory(Intake intake) {
		return repo.insertHistory(intake);
	}

	@Override
	public int editHistory(Intake intake) {
		return repo.updateHistory(intake);
	}

	@Override
	public int removeHistory(int intakeId) {
		return repo.deleteHistory(intakeId);
	}

}
