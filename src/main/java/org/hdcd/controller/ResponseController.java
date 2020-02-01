package org.hdcd.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.hdcd.domain.Address;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResponseController {
	private static final Logger logger = LoggerFactory.getLogger(ResponseController.class);
	
	// 컨트롤러 응답 : 1. void 타입
	@RequestMapping(value="/register01", method=RequestMethod.GET)
	public void register01() {
		logger.info("register01");
	};
	
	// 컨트롤러 응답 : 2. String 타입
	@RequestMapping(value="/register02", method=RequestMethod.GET)
	public String register02() {
		logger.info("register02");
		return "<<REGISTER02>> : SUCCESS(String Type Response)";
	}
	
	// 컨트롤러 응답 : 3. 자바빈즈 클래스 타입
	@RequestMapping(value="/register03", method=RequestMethod.GET, produces="application/json")
	public Address register03() {
		logger.info("register03");
		
		Address address = new Address();
		address.setPostCode("123456");
		address.setLocation("Seoul");
		
		return address;
	}

	// 컨트롤러 응답 : 4. 컬렉션 List 타입
	@RequestMapping(value="/register04", method=RequestMethod.GET)
	public List<Address> register04(){
		logger.info("register04");
		
		List<Address> addList = new ArrayList<>();
		
		Address address = new Address();
		address.setPostCode("123456");
		address.setLocation("Seoul");
		addList.add(address);
		
		Address address2 = new Address();
		address2.setPostCode("789101");
		address2.setLocation("Tokyo");
		addList.add(address2);
		
		return addList;
	}
	
	// 컨트롤러 응답 : 5. 컬렉션 Map 타입
	@RequestMapping(value="/register05", method=RequestMethod.GET)
	public Map<String, Address> register05() {
		logger.info("register05");
		
		Map<String, Address> map = new HashMap<>();
		Address address = new Address();
		address.setPostCode("123456");
		address.setLocation("Seoul");
		map.put("key1", address);
		
		Address address2 = new Address();
		address2.setPostCode("789101");
		address2.setLocation("Tokyo");
		map.put("key2", address2);
		
		return map;
	}
	
	// 컨트롤러 응답 : 6. ResponseEntity<Void> 타입
	// response 할 때 Http 헤더 정보와 내용을 가공하는 용도로 사용한다
	@RequestMapping(value="/register06", method=RequestMethod.GET)
	public ResponseEntity<Void> register06() {
		logger.info("register06");
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	// 컨트롤러 응답 : 7. ResponseEntity<String> 타입
	@RequestMapping(value="/register07", method=RequestMethod.GET)
	public ResponseEntity<String> register07() {
		logger.info("register07");
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
	
	// 컨트롤러 응답 : 8. ResponseEntity<자바 빈즈 클래스> 타입
	@RequestMapping(value="/register08", method=RequestMethod.GET)
	public ResponseEntity<Address> register08() {
		logger.info("register08");
		
		Address address = new Address();
		address.setPostCode("123456");
		address.setLocation("Seoul");
		return new ResponseEntity<Address>(address, HttpStatus.OK);
	}
	
	// 컨트롤러 응답 : 9. ResponseEntity<List> 타입
	@RequestMapping(value="/register09", method=RequestMethod.GET)
	public ResponseEntity<List<Address>> register09() {
		logger.info("register09");
		
		List<Address> addList = new ArrayList<>();
		
		Address address = new Address();
		address.setPostCode("123456");
		address.setLocation("Seoul");
		addList.add(address);
		
		Address address2 = new Address();
		address2.setPostCode("789101");
		address2.setLocation("Tokyo");
		addList.add(address2);
		
		return new ResponseEntity<List<Address>>(addList, HttpStatus.OK);
	}
	
	// 컨트롤러 응답 : 10. ResponseEntity<Map> 타입
	@RequestMapping(value="/register10", method=RequestMethod.GET)
	public ResponseEntity<Map<String, Address>> register10() {
		logger.info("register10");
		
		Map<String, Address> map = new HashMap<>();
		Address address = new Address();
		address.setPostCode("123456");
		address.setLocation("Seoul");
		map.put("key1", address);
		
		return new ResponseEntity<Map<String, Address>>(map, HttpStatus.OK);
	}
	
	// 컨트롤러 응답 : 11. ResponseEntity<byte[]> 타입
	// response 할 때 Http 헤더 정보와 바이너리 파일 데이터를 전달하는 용도로 사용한다
	@RequestMapping(value="/register11", method=RequestMethod.GET)
	public ResponseEntity<byte[]> register11() throws Exception {
		logger.info("register11");
		
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		
		try {
			HttpHeaders headers = new HttpHeaders();
			in = new FileInputStream("/Users/eunjisong/Documents/projects/DevProject/src/main/resources/static/img/shiba_inu.jpg");
			headers.setContentType(MediaType.IMAGE_JPEG);
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			in.close();
		}
		return entity;
	}
	
}
