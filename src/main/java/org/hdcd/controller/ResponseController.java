package org.hdcd.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResponseController {
	private static final Logger logger = LoggerFactory.getLogger(ResponseController.class);
	
	// 컨트롤러 응답 : void 타입
	@RequestMapping(value="/register01", method=RequestMethod.GET)
	public void register01() {
		logger.info("register01");
	};
	
	// 컨트롤러 응답 : String 타입
	@RequestMapping(value="/register02", method=RequestMethod.GET)
	public String register02() {
		logger.info("register02");
		return "<<REGISTER02>> : SUCCESS(String Type Response)";
	}
}
