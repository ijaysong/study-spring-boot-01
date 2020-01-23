package org.hdcd.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hdcd.domain.Board;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/boards")
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public ResponseEntity<List<Board>> list() {
		logger.info("list");
		
		List<Board> boardList = new ArrayList<>();
		
		Board board = new Board();
		board.setBoardNo(1);
		board.setTitle("향수");
		board.setContent("넓은 벌 동쪽 끝으로");
		board.setWriter("hongkd");
		board.setRegDate(new Date());
		
		board = new Board();
		board.setBoardNo(2);
		board.setTitle("첫 마음");
		board.setContent("날마다 새로우며 깊어지고 넓어진다");
		board.setWriter("hongkd");
		board.setRegDate(new Date());
		
		boardList.add(board);
		
		ResponseEntity<List<Board>> entity = new ResponseEntity<>(boardList, HttpStatus.OK);
		
		return entity;
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<String> register(@RequestBody Board board) {
		logger.info("register");
		
		ResponseEntity<String> entity = new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		return entity;
	}
	
	@RequestMapping(value = "/{boardNo}", method = RequestMethod.GET)
	public ResponseEntity<Board> read(@PathVariable("boardNo") int boardNo) {
		logger.info("read");
		
		Board board = new Board();
		
		board.setBoardNo(1);
		board.setTitle("향수");
		board.setContent("넓은 벌 동쪽 끝으로");
		board.setWriter("hongkd");
		board.setRegDate(new Date());
		
		ResponseEntity<Board> entity = new ResponseEntity<>(board, HttpStatus.OK);
		
		return entity;
	}
	
	@RequestMapping(value="/{boardNo}", method=RequestMethod.DELETE)
	public ResponseEntity<String> remove(@PathVariable("boardNo") int boardNo) {
		logger.info("remove");
		ResponseEntity<String> entity = new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		
		return entity;
	}
	
	@RequestMapping(value="/{boardNo}", method=RequestMethod.PUT)
	public ResponseEntity<String> remove(@PathVariable("boardNo") int boardNo, @RequestBody Board board) {
		logger.info("modify");
		ResponseEntity<String> entity = new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		
		return entity;
	}
}
