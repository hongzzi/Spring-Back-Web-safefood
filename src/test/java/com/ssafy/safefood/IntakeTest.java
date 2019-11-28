package com.ssafy.safefood;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ssafy.safefood.dto.Intake;
import com.ssafy.safefood.service.IntakeService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@SpringBootTest
class IntakeTest {

	@Autowired
	IntakeService service;
	
	@Test
	void test() {
//		List<Intake> list = service.getHistoryList("hongzzi.dev@gmail.com", new Date());
//		assertEquals(0, list.size());
//		log.trace("{}",list);
	}
	


}
