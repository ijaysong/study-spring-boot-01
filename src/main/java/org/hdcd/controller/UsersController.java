package org.hdcd.controller;

import java.net.URI;

import org.hdcd.domain.Member;
import org.hdcd.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
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
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<Void> register(@Validated @RequestBody Member member, UriComponentsBuilder uriBuilder) throws Exception {
		logger.info("register");
		
		service.register(member);
		
		logger.info("register member.getUserNo() = " + member.getUserNo());
		
		URI resourceUri = uriBuilder.path("users/{userNo}").buildAndExpand(member.getUserNo()).encode().toUri();
		
		return ResponseEntity.created(resourceUri).build();
	}
	
}
