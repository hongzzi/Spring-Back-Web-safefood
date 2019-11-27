package com.ssafy.safefood.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.safefood.dto.User;
import com.ssafy.safefood.service.JwtService;
import com.ssafy.safefood.service.UserService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/user")
//@CrossOrigin({"*"})
@Slf4j
public class UserRestController {

	@Autowired
	private JwtService jwtService;
	
	@Autowired
	UserService service;

	@PostMapping("/auth/signup")
	@ApiOperation("가입하기")
	public ResponseEntity<Map<String, Object>> postSignUp(@RequestBody User user) {
		try {
			int i = service.insertUser(user);
			
			if (i == 1) {
				return response(user, HttpStatus.CREATED, true);
			} else {
				return response("유효하지 않은 접근입니다.", HttpStatus.CONFLICT, false);
			}
		} catch (Exception e) {
			return response(e.getMessage(), HttpStatus.CONFLICT, false);
		}
	}

	@PostMapping("/auth/signin")
	@ApiOperation("로그인하기")
	public ResponseEntity<Map<String, Object>> postSignIn(@RequestBody User user,HttpServletResponse res) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			User reqUser = service.signin(user.getEmail(), user.getPassWord());
			if(reqUser != null) {
				String token = jwtService.create(reqUser);
				res.setHeader("jwt-auth-token", token);
				resultMap.put("status", true);
				resultMap.put("data", reqUser);
				return response(resultMap, HttpStatus.ACCEPTED, true);
			} else {
				return response("유효하지 않은 접근입니다.", HttpStatus.CONFLICT, false);
			}
		} catch (Exception e) {
			return response(e.getMessage(), HttpStatus.CONFLICT, false);
		}
	}

	
	
	@PutMapping("/update")
	@ApiOperation("회원정보 수정하기")
	public ResponseEntity<Map<String, Object>> updateUser(@RequestBody User user) {
		Map<String, Object> resultMap = new HashMap<>();
		try {
			int updateUser = service.updateUser(user);
			if(updateUser == 1) {
				return response(resultMap, HttpStatus.ACCEPTED, true);
			} else {
				return response("유효하지 않은 접근입니다.", HttpStatus.CONFLICT, false);
			}
		} catch (Exception e) {
			return response(e.getMessage(), HttpStatus.CONFLICT, false);
		}
	}
	
	@DeleteMapping("/remove")
	@ApiOperation("회원정보 탈퇴하기")
	public ResponseEntity<Map<String, Object>> deleteUser(@RequestParam String email) {
		Map<String, Object> resultMap = new HashMap<>();
		try {
			int updateUser = service.deleteUser(email);
			if(updateUser == 1) {
				return response(resultMap, HttpStatus.ACCEPTED, true);
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
