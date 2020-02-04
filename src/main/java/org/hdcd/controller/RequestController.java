package org.hdcd.controller;

import java.util.Calendar;
import java.util.List;

import org.hdcd.domain.Address;
import org.hdcd.domain.Card;
import org.hdcd.domain.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	// 객체 타입의 JSON 요청 데이터를 @RequestBody 애너테이션을 지정하여 자바빈즈 매개변수로 처리한다
	@RequestMapping(value="/request05", method=RequestMethod.POST)
	public ResponseEntity<String> request05(@RequestBody Member member) {
		logger.info("request05");
		
		logger.info("userId: " + member.getUserId());
		logger.info("password: " + member.getPassword());
		
		ResponseEntity<String> entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		return entity;
	}
	
	// 객체 타입의 JSON 요청 데이터를 @PathVariable 애너테이션을 지정한 문자열 매개변수와 @RequestBody 애너테이션을 지정한 자바빈즈 매개변수로 지정한다
	@RequestMapping(value="/request06/{userId}", method=RequestMethod.POST)
	public ResponseEntity<String> request06(@PathVariable("userId") String userId, @RequestBody Member member) {
		logger.info("request06");
		
		logger.info("userId: " + userId);
		logger.info("member.getUserId(): " + member.getUserId());
		logger.info("member.getPassword(): " + member.getPassword());
		
		ResponseEntity<String> entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		return entity;
	}
	
	// 객체 배열 타입의 JSON 요청 데이터를 자바빈즈요소를 가진 리스트 컬렉션 매개변수에 @RequestBody 애너테이션을 지정하여 처리한다
	@RequestMapping(value="/request07", method=RequestMethod.POST)
	public ResponseEntity<String> request07(@RequestBody List<Member> memberList) {
		logger.info("register07");
		
		for(Member member : memberList) {
			logger.info("userId: " + member.getUserId());
			logger.info("password: " + member.getPassword());
		}
		
		ResponseEntity<String> entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		return entity;
	}
	
	// 중첩된 객체 타입의 JSON 요청 데이터를 @RequestBody 애너테이션을 지정하여 중첩된 자바빈즈 매개변수로 처리한다
	@RequestMapping(value="/request08", method=RequestMethod.POST)
	public ResponseEntity<String> request08(@RequestBody Member member) {
		logger.info("register08");
		
		logger.info("userId: " + member.getUserId());
		logger.info("password: " + member.getPassword());
		
		Address address= member.getAddress();
		
		if(address != null) {
			logger.info("address.getPostCode() = " + address.getPostCode());
			logger.info("address.getLocation() = " + address.getLocation());
		} else {
			logger.info("address == null");
		}
		
		ResponseEntity<String> entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		return entity;
	}
	
	// 중첩된 객체 배열 타입의 JSON 요청 데이터를 @RequestBody 애너테이션을 지정하여 중첩된 컬렉션 자바빈즈 매개변수로 처리한다
	@RequestMapping(value="/request09", method=RequestMethod.POST)
	public ResponseEntity<String> request09(@RequestBody Member member) {
		logger.info("register09");
		
		logger.info("userId: " + member.getUserId());
		logger.info("password: " + member.getPassword());
		
		List<Card> cardList= member.getCardList();
		
		if(cardList != null) {
			logger.info("cardList.size() = " + cardList.size());
			
			for(int i = 0; i < cardList.size(); i++) {
				Card card = cardList.get(i);
				logger.info("card.getNo() = " + card.getNo());
				logger.info("card.getValidMonth() = " + card.getValidMonth());
			}
		} else {
			logger.info("cardList == null");
		}
		
		ResponseEntity<String> entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		return entity;
	}
	
	// yyyy/MM/dd 날짜 문자열 형식은 Date 타입으로 변환에 실패한다
	@RequestMapping(value="/date01", method=RequestMethod.POST)
	public ResponseEntity<String> date01(@RequestBody Member member) {
		logger.info("date01");
		logger.info("userId = " + member.getUserId());
		logger.info("password = " + member.getPassword());
		logger.info("member.getDateOfBirth() = " + member.getDateOfBirth());
		
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
	
	// yyyyMMdd 날짜 문자열 형식은 Date 타입으로 변환에 실패한다
	@RequestMapping(value="/date02", method=RequestMethod.POST)
	public ResponseEntity<String> date02(@RequestBody Member member) {
		logger.info("date02");
		logger.info("userId = " + member.getUserId());
		logger.info("password = " + member.getPassword());
		logger.info("member.getDateOfBirth() = " + member.getDateOfBirth());
		
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
	
	// yyyy-MM-dd 날짜 문자열 형식은 Date 타입으로 성공적으로 변환된다
	@RequestMapping(value="/date03", method=RequestMethod.POST)
	public ResponseEntity<String> date03(@RequestBody Member member) {
		logger.info("date03");
		logger.info("userId = " + member.getUserId());
		logger.info("password = " + member.getPassword());
		logger.info("member.getDateOfBirth() = " + member.getDateOfBirth());
		
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
	
	// 자바 Date 타입의 JSON 날짜 형식을 @JsonFormat 애너테이션으로 지정하지 않으면 날짜 숫자값으로 표시된다
	@RequestMapping(value="/read", method=RequestMethod.GET)
	public ResponseEntity<Member> read() {
		logger.info("read");
		Member member = new Member();
		member.setUserId("hongkd");
		member.setPassword("1234");
		
		Calendar cal = Calendar.getInstance();
		
		int year = 1992;
		int month = 8;
		int date = 11;
		cal.set(year, month-1, date);
		
		member.setDateOfBirth(cal.getTime());
		
		return new ResponseEntity<Member>(member, HttpStatus.OK);
	}
}
