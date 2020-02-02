package org.hdcd.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestController {
	private static final Logger logger = LoggerFactory.getLogger(RequestController.class);

	// URL 경로 상의 경로 변수 값을 @PathVariable 애너테이션을 지정하여 문자열 매개변수로 처리한다
	@RequestMapping(value="/request01/{userId}", method=RequestMethod.GET)
	public ResponseEntity<String> request01 (@PathVariable("userId")String userId) {
		logger.info("request01");
		logger.info("userId= "+userId);
		
		ResponseEntity<String> entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		return entity;
	}
	
	// URL 경로 상의 여러 개의 경로 변수 값을 @PathVariable 애너테이션을 지정하여 여러 개의 문자열 매개변수로 처리한다
	@RequestMapping(value="/request02/{userId}/{password}", method=RequestMethod.POST)
	public ResponseEntity<String> request02 (@PathVariable("userId") String userId, @PathVariable("password") String password){
		logger.info("request02");
		logger.info("userId= " + userId);
		logger.info("password= " + password);
		
		ResponseEntity<String> entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		return entity;
	}
	
	// 객체 타입의 JSON 요청 데이터는 문자열 매개변수로 처리할 수 없다
	@RequestMapping(value="/request03", method=RequestMethod.POST)
	public ResponseEntity<String> request03 (String userId) {
		logger.info("request03");
		logger.info("userId= " + userId);
		
		ResponseEntity<String> entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		return entity;
	}
	
	// 요청 URL에 쿼리파라미터를 붙여서 전달하면 @RequestParam를 활용하여 문자열 매개변수로 처리한다
	@RequestMapping(value="/request04", method=RequestMethod.POST)
	public ResponseEntity<String> request04(@RequestParam("userId") String userId, @RequestParam("password") String password) {
		logger.info("request04");
		logger.info("userId= " + userId);
		logger.info("password= " + password);
		
		ResponseEntity<String> entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		return entity;
	}
}
