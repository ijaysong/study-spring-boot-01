package org.hdcd.controller;

import java.net.URI;
import java.util.List;

import org.hdcd.domain.Member;
import org.hdcd.domain.MemberAuth;
import org.hdcd.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

	private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);
	
	@Autowired
	private TransactionService service;
	
	@RequestMapping(value="/{userNo}", method=RequestMethod.GET)
	public ResponseEntity<Member> read(@PathVariable("userNo") int userNo) throws Exception {
		logger.info("read");
		
		Member member = service.read(userNo);
		
		return new ResponseEntity<>(member, HttpStatus.OK);
	}
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public ResponseEntity<List<Member>> list() throws Exception {
		logger.info("list");
		
		return new ResponseEntity<>(service.list(), HttpStatus.OK);
	}
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<Void> register(@Validated @RequestBody Member member, UriComponentsBuilder uriBuilder) throws Exception {
		logger.info("register");
		
		service.register(member);
		
		logger.info("register member.getUserNo()= " + member.getUserNo());
		
		URI resourceUri = uriBuilder.path("transaction/{userNo}").buildAndExpand(member.getUserNo()).encode().toUri();
		
		return ResponseEntity.created(resourceUri).build();
	}
	
	@RequestMapping(value="/delete/{userNo}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> remove(@PathVariable("userNo") int userNo) throws Exception {
		logger.info("remove");
		
		service.remove(userNo);
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value="/modify/{userNo}", method=RequestMethod.PUT)
	public ResponseEntity<Void> modify(@PathVariable("userNo") int userNo, @RequestBody Member member) throws Exception {
		logger.info("modify");
		
		logger.info("modify userNo = " + userNo);
		
		List<MemberAuth> authList = member.getAuthList();
		
		logger.info("modify authList.size() = " + authList.size());
		
		for(int i = 0; i < authList.size(); i++) {
			MemberAuth memberAuth = authList.get(i);
			
			logger.info("modify memberAuth.getAuth() = " + memberAuth.getAuth());
		}
		
		member.setUserNo(userNo);
		service.modify(member);
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
