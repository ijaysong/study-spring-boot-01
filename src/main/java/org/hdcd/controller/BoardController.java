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
	
	// GET방식
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
	
	// POST방식
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<String> register(Board board) {
		// Q.파라미터의 @RequestBody를 지우고 나서 $.post의 415에러(Unsupported Media Type)가 해결되었다. 왜??
		logger.info("register");
		
		return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
	}
	
	// GET방식
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
	
	// DELETE방식
	@RequestMapping(value="/{boardNo}", method=RequestMethod.DELETE)
	public ResponseEntity<String> remove(@PathVariable("boardNo") int boardNo) {
		logger.info("remove");
		ResponseEntity<String> entity = new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		
		return entity;
	}
	
	// PUT방식
	@RequestMapping(value="/{boardNo}", method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<String> modifyByPut(@PathVariable("boardNo") int boardNo, @RequestBody Board board) {
		// @RequestBody:컨트롤러로 전송된 JSON이 해당 데이터 타입으로 받을 수 있도록 변환해준다.
		// @ResponseBody:컨트롤러에서 반환되는 값을 JSON으로 변환하여 보낸다. 
		logger.info("modify");
		ResponseEntity<String> entity = new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		
		return entity;
	}
	
	@RequestMapping(value="/read/{boardNo}")
	public String readBoard(@PathVariable("boardNo") int boardNo) {
		logger.info("read boardNo:" + boardNo);
		return "READ boardNo:"+boardNo;
	}
	
	@RequestMapping(value="/read2/{no}")
	public String readBoard2(@PathVariable("no") int boardNo) {
		logger.info("read2 boardNo:" + boardNo);
		return "READ2 boardNo:"+boardNo;
	}
	
	// Patch방식
	@RequestMapping(value="/{boardNo}", method=RequestMethod.PATCH)
	public ResponseEntity<String> modifyByPatch(@PathVariable("boardNo") int boardNo, @RequestBody Board board) {
		logger.info("modifyByPatch");
		
		ResponseEntity<String> entity = new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		return entity;
	}
	
	// Header 속성을 사용하여 요청을 매핑한다 
	@RequestMapping(value="/{boardNo}", method=RequestMethod.PUT, headers="X-HTTP-Method-Override=PUT")
	public ResponseEntity<String> modifyByHeader(@PathVariable("boardNo") int boardNo, @RequestBody Board board){
		logger.info("modifyByHeader");
		
		ResponseEntity<String> entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		return entity;
	}
	
	// consumes 속성을 사용하여 미디어 타입을 지정한다
	// consumes 속성을 지정하지 않으면, 기본값인 application/json"이 지정된다
	@RequestMapping(value="/{boardNo}", method=RequestMethod.PUT, consumes="application/xml")
	public ResponseEntity<String> modifyByJson(@PathVariable("boardNo") int boardNo, @RequestBody Board board){
		logger.info("modifyByXml boardNo: " + boardNo);
		logger.info("modifyByXml board: " + board);
		
		ResponseEntity<String> entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		return entity;
	}
	
	// produces 속성 값에 "application/xml" 미디어 타입을 지정한다
	@RequestMapping(value="/{boardNo}", method=RequestMethod.GET, produces="application/xml")
	public ResponseEntity<Board> readToJson(@PathVariable("boardNo") int boardNo) {
		logger.info("readToXml");
		
		Board board = new Board();
		
		board.setTitle("제목");
		board.setContent("내용입니다");
		board.setWriter("홍길동");
		board.setRegDate(new Date());
		
		ResponseEntity<Board> entity = new ResponseEntity<Board>(board, HttpStatus.OK);
		return entity;
	}
}
