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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.safefood.dto.Board;
import com.ssafy.safefood.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin({"*"})
@RequestMapping("/board")
public class BoardRestController {
	@Autowired
	BoardService bService;
	
	@GetMapping("/notice/pageSize")
	public ResponseEntity<Map<String, Object>> getNoticeBoardPageSize(@RequestParam int condition, @RequestParam String text) {
		try {
			int pageSize = bService.getNoticeBoardPageSize(condition, text);
			return response(pageSize, HttpStatus.OK, true);
		} catch (RuntimeException e) {
			log.error("공지사항 페이지 리스트 불러오기 실패", e);
			return response(e.getMessage(), HttpStatus.CONFLICT, false);
		}
	}
	
	@GetMapping("/notice/{page}")
	public ResponseEntity<Map<String, Object>> getNoticeBoardList(@PathVariable int page, @RequestParam int condition, @RequestParam String text) {
		try {
			List<Board> noticeBoardList = bService.getNoticeBoardList(page, condition, text);
			return response(noticeBoardList, HttpStatus.OK, true);
		} catch (RuntimeException e) {
			log.error("공지사항 페이지 리스트 불러오기 실패", e);
			return response(e.getMessage(), HttpStatus.CONFLICT, false);
		}
	}
	
	@PostMapping("/notice/insert")
	public ResponseEntity<Map<String, Object>> insertNoticeBoard(@RequestBody Board board) {
		try {
			int insertResult = bService.insertNoticeBoard(board);
			if(insertResult>0) {
				return response(board.getBoardId(), HttpStatus.CREATED, true);
			} else {
				return response(-1, HttpStatus.CONFLICT, false);
			}
		} catch (RuntimeException e) {
			log.error("공지사항 페이지 리스트 불러오기 실패", e);
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
