package org.hdcd.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate);
		
		return "home";
	}
	
	@RequestMapping(value="/ajaxHome", method=RequestMethod.GET)
	public String ajaxHome() {
		return "ajaxHome";
	}
	
	@RequestMapping(value="/responseHome", method=RequestMethod.GET)
	public String responseHome() {
		return "responseHome";
	}
	
	@RequestMapping(value="/requestHome", method=RequestMethod.GET)
	public String requestHome() {
		return "requestHome";
	}
	
	@RequestMapping(value="/registerForm", method=RequestMethod.GET)
	public String registerForm() {
		return "registerForm";
	}
	
	@RequestMapping(value="/crudHome", method=RequestMethod.GET)
	public String crudHome() {
		return "crudHome";
	}
	
	@RequestMapping(value="/memberHome", method=RequestMethod.GET)
	public String memberHome() {
		return "memberHome";
	}
	
	@RequestMapping(value="/itemHome", method=RequestMethod.GET)
	public String itemHome() {
		return "itemHome";
	}
	
	@RequestMapping(value="/aopHome", method=RequestMethod.GET)
	public String aopHome() {
		return "aopHome";
	}
	
	@RequestMapping(value="/transactionHome", method=RequestMethod.GET)
	public String transactionHome() {
		return "transactionHome";
	}
	
	@RequestMapping(value="/errorHandlingHome", method=RequestMethod.GET)
	public String errorHandlingHome() {
		return "errorHandlingHome";
	}
	
	@RequestMapping(value="/interceptorHome", method=RequestMethod.GET)
	public String interceptorHome() {
		return "interceptorHome";
	}
	
	@RequestMapping(value="/jwtHome", method=RequestMethod.GET)
	public String jwtHome() {
		return "jwtHome";
	}
}
