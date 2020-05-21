package org.hdcd.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jwt/boards")
public class JwtBoardController {

	private static final Logger logger = LoggerFactory.getLogger(JwtBoardController.class);
	
	// 모든 사용자가 접근이 가능하다
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<String> list() throws Exception {
		logger.info("list : access to all");
		
		return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
	}
	
	// 회원권한을 가진 사용자만 접근이 가능하다
	@PreAuthorize("hasAnyRole('ROLE_MEMBER')")
	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<String> register() {
		logger.info("register : access to member");
		
		return  new ResponseEntity<>("SUCCESS", HttpStatus.OK);
	}
	
	// 로그인한 사용자만 접근이 가능하다
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value="/{boardNo}", method=RequestMethod.GET)
	public ResponseEntity<String> read(@RequestHeader(name="Authorization") String header, @PathVariable("boardNo") int boardNo) {
		logger.info("read : access tot authentication user");
		logger.info("read : header = " + header);
		logger.info("read : boardNo = " + boardNo);
		
		if(header == null || !header.startsWith("Bearer")) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		String authToken = header.substring(7);
		
		logger.info("read : authToken" + authToken);
		
		return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
	}
	
	// 관리자나 회원권한을 가진 사용자만 접근이 가능하다
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MEMBER')")
	@RequestMapping(value="/{boardNo}", method=RequestMethod.PUT)
	public ResponseEntity<String> modify(@PathVariable("boardNo") int boardNo) {
		logger.info("modifyForm : access to admin or member");
		
		return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
	}
}
