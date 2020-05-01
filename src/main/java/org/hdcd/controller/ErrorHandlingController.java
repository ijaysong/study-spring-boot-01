package org.hdcd.controller;

import java.util.List;

import org.hdcd.domain.Board;
import org.hdcd.service.ErrorHandlingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/errorHandling")
public class ErrorHandlingController {
	
	private static final Logger logger = LoggerFactory.getLogger(ErrorHandlingController.class);
	
	@Autowired
	private ErrorHandlingService service;
	
	// 에러가 발생할 상황을 가정한다.
	// Case 1. 등록할 때 제목에 빈 값을 입력하여 유효값 검증 시,예외 발생
	// Case 2. 삭제 할 때 매핑 파일에서 예외 발생
	// Case 3. 존재하지 않는 게시물을 조회할 때 사용자가 정의한 예외 발생
	
	@RequestMapping(value="/{boardNo}", method=RequestMethod.GET)
	public ResponseEntity<Board> read(@PathVariable("boardNo") int boardNo) throws Exception {
		logger.info("read");
		
		ResponseEntity<Board> entity = null;
		
		// try-catch문으로 예외를 처리한다
		try {
			// 게시판의 글이 존재하지 않으면, 사용자가 정의한 예외를 발생시킨다.
			Board board = service.read(boardNo);
			entity = new ResponseEntity<>(board, HttpStatus.OK);
			
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public ResponseEntity<List<Board>> list() throws Exception {
		logger.info("list");
		
		ResponseEntity<List<Board>> entity = null;
		
		// try-catch문으로 예외를 처리한다
		try {
			entity = new ResponseEntity<>(service.list(), HttpStatus.OK);
			
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	
	@RequestMapping(value="", method=RequestMethod.POST)
	// @Validated 애너테이션을 사용하면 Bean Validation의 유효성 검증 매커니즘을 이용할 수 있다
	// BindingResult 타입의 매개변수를 지정하면 BindingResult 매개변수가 입력값 검증 예외를 처리한다
	public ResponseEntity<String> register(@Validated @RequestBody Board board, BindingResult result) throws Exception {
		logger.info("register");
		logger.info("result.hasError() = " + result.hasErrors());
		
		// 입력값 검증 예외가 발생하면 예외메시지를 응답한다
		if(result.hasErrors()) {
			return new ResponseEntity<String>(result.toString(), HttpStatus.BAD_REQUEST);
		}
		
		logger.info("board.getTitle() = " + board.getTitle());
		
		ResponseEntity<String> entity = null;
		
		// try-catch문으로 예외를 처리한다
		try {
			// 제목에 빈 값을 입력하여 유효값 검증 예외 발생
			service.register(board);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@RequestMapping(value="/{boardNo}", method=RequestMethod.DELETE)
	public ResponseEntity<String> remove(@PathVariable("boardNo") int boardNo) throws Exception {
		logger.info("remove");
		
		ResponseEntity<String> entity = null;
		
		// try-catch문으로 예외를 처리한다
		try {
			// Mybatis Mapper에서 에러 발생
			service.remove(boardNo);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@RequestMapping(value="/{boardNo}", method=RequestMethod.PUT)
	public ResponseEntity<String> modify(@PathVariable("boardNo") int boardNo, @Validated @RequestBody Board board) throws Exception {
		logger.info("modify");
		
		ResponseEntity<String> entity = null;
		
		// try-catch문으로 예외를 처리한다
		try {
			board.setBoardNo(boardNo);
			service.modify(board);
			
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}

}
