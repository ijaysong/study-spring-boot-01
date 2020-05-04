package org.hdcd.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	@RequestMapping("/register")
	public void registerForm() {
		logger.info("Register Form : access to member");
	}
}
