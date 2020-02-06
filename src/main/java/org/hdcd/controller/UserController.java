package org.hdcd.controller;

import org.hdcd.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/users")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping(value="", method=RequestMethod.POST, produces="text/plain;charset=UTF-8")
	// 입력값 검증을 할 도메인 클래스에 @Validated를 지정한다
	public ResponseEntity<String> register(@Validated @RequestBody User user, BindingResult result) {
		logger.info("register");
		
		if(result.hasErrors()) {
			ResponseEntity<String> entity = new ResponseEntity<String>(result.toString(), HttpStatus.BAD_REQUEST);
			return entity;
		}
		
		logger.info("user.getUserId() = " + user.getUserId()); 
		logger.info("user.getpassword() = " + user.getPassword());
		logger.info("user.getUserName() = " + user.getUserName());
		logger.info("user.getEmail() = " + user.getEmail());
		logger.info("user.getGender() = " + user.getGender());
		
		ResponseEntity<String> entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		return entity;
	}
}
