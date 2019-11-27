package com.ssafy.safefood.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.safefood.dto.Food;
import com.ssafy.safefood.service.MenuService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin({"*"})
@RequestMapping("/menu")
public class MenuRestController {
	@Autowired
	MenuService mService;
	
	@GetMapping("/list/week")
	public ResponseEntity<Map<String, Object>> getWeekdayMenuList(@RequestParam Date startDate, @RequestParam Date endDate) {
		try {
			List<Food> relativeFoodList= mService.getWeekdayMenuList(startDate, endDate);
			return response(relativeFoodList, HttpStatus.OK, true);
		} catch (RuntimeException e) {
			log.error("일주일치 식단 불러오기 실패", e);
			return response(e.getMessage(), HttpStatus.CONFLICT, false);
		}
	}

	private ResponseEntity<Map<String, Object>> response(Object data, HttpStatus httpStatus, boolean status){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("status", status);
        resultMap.put("data", data);
        return new ResponseEntity<Map<String,Object>>(resultMap, httpStatus);
	}
}
