package org.hdcd.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping("/login")
	// 에러 메시지와 로그아웃 메시지를 파라미터로 사용
	public String loginForm(String error, String logout, Model model) {
		logger.info("error: " + error);
		logger.info("logout: " + logout);
		
		if(error != null) {
			model.addAttribute("error", "Login Error!");
		}
		
		if(logout != null) {
			model.addAttribute("logout", "Logout!");
		}
		
		return "loginForm";
	}
	
	@RequestMapping("/logout")
	public String logoutForm() {
		logger.info("Logout Form");
		
		return "logoutForm";
	}
}
