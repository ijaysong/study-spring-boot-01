package org.hdcd.controller;

import java.net.URI;
import java.util.List;

import org.hdcd.domain.Board;
import org.hdcd.service.AopService;
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
@RequestMapping("/aop")
public class AopController {
	
	private static final Logger logger = LoggerFactory.getLogger(AopController.class);
	
	@Autowired
	private AopService service;
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<Void> register(@Validated @RequestBody Board board, UriComponentsBuilder uriBuilder) throws Exception {
		logger.info("register");
		
		service.register(board);
		
		logger.info("register board.getBoard() = " + board.getBoardNo());
		
		URI resourceUri = uriBuilder.path("aop/{boardNo}").buildAndExpand(board.getBoardNo()).encode().toUri();
		
		return ResponseEntity.created(resourceUri).build();
	}
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public ResponseEntity<List<Board>> list() throws Exception {
		logger.info("list");
		
		return new ResponseEntity<>(service.list(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{boardNo}", method=RequestMethod.GET)
	public ResponseEntity<Board> read(@PathVariable("boardNo") int boardNo) throws Exception {
		logger.info("read");
		
		Board board = service.read(boardNo);
		return new ResponseEntity<>(board, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{boardNo}", method=RequestMethod.PUT)
	public ResponseEntity<Void> modify(@PathVariable("boardNo") int boardNo, @Validated @RequestBody Board board) throws Exception {
		logger.info("modify");
		
		board.setBoardNo(boardNo);
		service.modify(board);
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value="/{boardNo}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> remove(@PathVariable("boardNo") int boardNo) throws Exception {
		logger.info("remove");
		
		service.remove(boardNo);
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
