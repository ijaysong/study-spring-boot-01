package org.hdcd.controller;

import org.hdcd.domain.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/interceptor")
public class InterceptorController {

	private static final Logger logger = LoggerFactory.getLogger(InterceptorController.class);
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ResponseEntity<String> login(@RequestBody Member member) {
		logger.info("login");
		
		logger.info("login userId = " + member.getUserId());
		logger.info("login userPw = " + member.getPassword());
		
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
}
