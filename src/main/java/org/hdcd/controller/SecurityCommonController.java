package org.hdcd.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityCommonController {

	private static final Logger logger = LoggerFactory.getLogger(SecurityCommonController.class);
	
	@RequestMapping("/accessError")
	public void accessDenied(Authentication auth, Model model) {
		logger.info("Access Denied : " + auth);
		
		model.addAttribute("msg", "Access Denied.");
	}
}
