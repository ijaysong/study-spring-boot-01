package org.hdcd.controller;

import java.net.URI;
import java.util.List;

import org.hdcd.domain.Member;
import org.hdcd.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@RequestMapping("/users")
public class UsersController {
	private static final Logger logger = LoggerFactory.getLogger(UsersController.class);
	
	@Autowired
	private MemberService service;
	
	// 등록
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ResponseEntity<Void> register(@Validated @RequestBody Member member, UriComponentsBuilder uriBuilder) throws Exception {
		logger.info("register");
		
		service.register(member);
		
		logger.info("register member.getUserNo() = " + member.getUserNo());
		
		URI resourceUri = uriBuilder.path("users/{userNo}").buildAndExpand(member.getUserNo()).encode().toUri();
		
		return ResponseEntity.created(resourceUri).build();
	}
	
	// 목록 조회
	@RequestMapping(value="", method=RequestMethod.GET)
	public ResponseEntity<List<Member>> list() throws Exception {
		logger.info("list");
		
		return new ResponseEntity<>(service.list(), HttpStatus.OK);
	}
	
	// 상세 조회
	@RequestMapping(value="/read/{userNo}", method=RequestMethod.GET)
	public ResponseEntity<Member> read(@PathVariable("userNo") int userNo) throws Exception {
		logger.info("read");
		
		Member member = service.read(userNo);
		
		return new ResponseEntity<>(member, HttpStatus.OK);
	}
	
	// 수정
	@RequestMapping(value="/modify/{userNo}", method=RequestMethod.PUT)
	public ResponseEntity<Void> modify(@PathVariable("userNo") int userNo, @Validated @RequestBody Member member) throws Exception {
		logger.info("modify");
		
		member.setUserNo(userNo);
		service.modify(member);
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	// 삭제
	@RequestMapping(value="/delete/{userNo}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> remove(@PathVariable("userNo") int userNo) throws Exception {
		logger.info("remove");
		
		service.remove(userNo);
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}
