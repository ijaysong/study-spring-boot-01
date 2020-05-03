package org.hdcd.controller;

import java.util.List;

import org.hdcd.domain.Board;
import org.hdcd.domain.Member;
import org.hdcd.service.InterceptorService;
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

@RestController
@RequestMapping("/interceptor")
public class InterceptorController {

	private static final Logger logger = LoggerFactory.getLogger(InterceptorController.class);
	
	@Autowired
	private InterceptorService service;
	
	@RequestMapping(value="/{boardNo}", method=RequestMethod.GET)
	public ResponseEntity<Board> read(@PathVariable("boardNo") int boardNo) throws Exception {
		logger.info("read");
		
		Board board = service.read(boardNo);
		
		return new ResponseEntity<>(board, HttpStatus.OK);
	}
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public ResponseEntity<List<Board>> list() throws Exception {
		logger.info("list");
		
		return new ResponseEntity<>(service.list(), HttpStatus.OK);
	}
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<String> register(@Validated @RequestBody Board board) throws Exception {
		logger.info("register");
		
		service.register(board);
		
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
	
	@RequestMapping(value="/{boardNo}", method=RequestMethod.DELETE)
	public ResponseEntity<String> remove(@PathVariable("boardNo") int boardNo) throws Exception {
		logger.info("remove");
		
		service.remove(boardNo);
		
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
	
	@RequestMapping(value="/{boardNo}", method=RequestMethod.PUT)
	public ResponseEntity<String> modify(@PathVariable("boardNo") int boardNo, @RequestBody Board board) throws Exception {
		logger.info("modify");
		
		board.setBoardNo(boardNo);
		service.modify(board);
		
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ResponseEntity<String> login(@RequestBody Member member) {
		logger.info("login");
		
		logger.info("login userId = " + member.getUserId());
		logger.info("login userPw = " + member.getPassword());
		
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
}
