package com.ssafy.safefood.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.safefood.dto.Intake;
import com.ssafy.safefood.service.IntakeService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin({ "*" })
@RequestMapping("/history")
public class HistoryRestController {

	@Autowired
	IntakeService service;

	@GetMapping("/intake")
	@ApiOperation("섭취정보 조회하기")
	public ResponseEntity<Map<String, Object>> getHistoryByDate(@RequestParam String email, @RequestParam Date intakeDate) {
		System.out.println(email +" : "+ intakeDate);
		try {
			List<Intake> list = service.getHistoryList(email, intakeDate);
			log.trace("get History date : {}",list);
			return response(list, HttpStatus.ACCEPTED, false);
		} catch (Exception e) {
			return response(e.getMessage(), HttpStatus.CONFLICT, false);
		}
	}

	@PostMapping("/intake")
	@ApiOperation("섭취정보 등록하기")
	public ResponseEntity<Map<String, Object>> addHistory(@RequestBody Intake intake) {
		try {
			int insertHistory = service.addHistory(intake);
			System.out.println(intake.toString());
			if (insertHistory == 1) {
				log.trace("add History date : {}",insertHistory);
				return response(insertHistory, HttpStatus.ACCEPTED, true);
			} else {
				return response("유효하지 않은 접근입니다.", HttpStatus.CONFLICT, false);
			}
		} catch (Exception e) {
			return response(e.getMessage(), HttpStatus.CONFLICT, false);
		}
	}

	@PutMapping("/intake")
	@ApiOperation("섭취정보 수정하기")
	public ResponseEntity<Map<String, Object>> updateHistory(@RequestBody Intake intake) {
		try {
			int updateHistory = service.editHistory(intake);
			if (updateHistory == 1) {
				log.trace("update History date : {}",updateHistory);
				return response(updateHistory, HttpStatus.ACCEPTED, true);
			} else {
				return response("유효하지 않은 접근입니다.", HttpStatus.CONFLICT, false);
			}
		} catch (Exception e) {
			return response(e.getMessage(), HttpStatus.CONFLICT, false);
		}
	}

	@DeleteMapping("/intake/{intakeId}")
	@ApiOperation("섭취정보 삭제하기")
	public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable int intakeId) {
		try {
			int deleteHistory = service.removeHistory(intakeId);
			if (deleteHistory == 1) {
				log.trace("delete History date : {}",deleteHistory);
				return response(deleteHistory, HttpStatus.ACCEPTED, true);
			} else {
				return response("유효하지 않은 접근입니다.", HttpStatus.CONFLICT, false);
			}
		} catch (Exception e) {
			return response(e.getMessage(), HttpStatus.CONFLICT, false);
		}
	}
	
	private ResponseEntity<Map<String, Object>> response(Object data, HttpStatus httpstatus, boolean status) {
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("status", status);
		resultMap.put("data", data);
		return new ResponseEntity<Map<String, Object>>(resultMap, httpstatus);
	}
}
