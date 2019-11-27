package com.ssafy.safefood.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.safefood.dto.Food;
import com.ssafy.safefood.service.FoodService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin({"*"})
@RequestMapping("/food")
public class FoodRestController {
	@Autowired
	FoodService fService;
	
	@GetMapping("/list/{page}")
	public ResponseEntity<Map<String, Object>> listFoods(@PathVariable int page, @RequestParam int condition, @RequestParam String text) {
		try {
			List<Food> foodList = fService.listFoods(page, condition, text);
			return response(foodList, HttpStatus.OK, true);
		} catch (RuntimeException e) {
			log.error("식품 검색 리스트 불러오기 실패", e);
			return response(e.getMessage(), HttpStatus.CONFLICT, false);
		}
	}
	
	@GetMapping("/list/pageSize")
	public ResponseEntity<Map<String, Object>> getFoodsPageSize(@RequestParam int condition, @RequestParam String text) {
		try {
			int pageSize = fService.getFoodsPageSize(condition, text);
			return response(pageSize, HttpStatus.OK, true);
		} catch (RuntimeException e) {
			log.error("식품 페이지 크기 불러오기 실패", e);
			return response(e.getMessage(), HttpStatus.CONFLICT, false);
		}
	}
	
	@GetMapping("detail/relative")
	public ResponseEntity<Map<String, Object>> listRelativeFoods(@RequestParam int code) {
		try {
			List<Food> relativeFoodList= fService.listRelativeFoods(code);
			return response(relativeFoodList, HttpStatus.OK, true);
		} catch (RuntimeException e) {
			log.error("식품 페이지 크기 불러오기 실패", e);
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
