package org.hdcd.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/security/board")
@Controller
public class SecurityBoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(SecurityBoardController.class);
	
	@RequestMapping("/list")
	public void list() {
		logger.info("List : access to all");
	}
	
	// 회원 권한을 가진 사용자만 접근이 가능하다.
	@PreAuthorize("hasRole('ROLE_MEMBER')")
	@RequestMapping("/register")
	public void registerForm() {
		logger.info("Register Form : access to member");
	}
	
	// 로그인한 사용자만 접근이 가능하다.
	@PreAuthorize("isAuthenticated()")
	@RequestMapping("/read")
	public void read() {
		logger.info("read : access to authentication user");
	}
	
	// 관리자나 회원권한을 가진 사용자만 접근이 가능하다.
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MEMBER')")
	@RequestMapping("/modify")
	public void modifyForm() {
		logger.info("modifyForm : access to member or admin");
	}
 }
