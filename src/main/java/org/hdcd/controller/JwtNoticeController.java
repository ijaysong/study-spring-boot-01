package org.hdcd.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jwt/notices")
public class JwtNoticeController {
	
	private static final Logger logger = LoggerFactory.getLogger(JwtNoticeController.class);
	
	// 모든 사용자가 접근이 가능하다
	@RequestMapping(value="", method=RequestMethod.GET)
	public ResponseEntity<String> list() throws Exception {
		logger.info("list : access to all");
		
		return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
	}
	
	// 관리자권한을 가진 사용자만 접근이 가능하다
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<String> register() {
		logger.info("register : access to admin");
		
		return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
	}
}
