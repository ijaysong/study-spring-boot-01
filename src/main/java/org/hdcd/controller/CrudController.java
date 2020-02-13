package org.hdcd.controller;

import java.net.URI;

import org.hdcd.domain.Board;
import org.hdcd.service.CrudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/bbs")
public class CrudController {
	private static final Logger logger = LoggerFactory.getLogger(CrudController.class);
	
	@Autowired
	private CrudService service;
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ResponseEntity<String> register(@Validated @RequestBody Board board, UriComponentsBuilder uriBuilder) throws Exception{
		logger.info("register");
		
		service.register(board);
		
		logger.info("register board.getBoardNo() = " + board.getBoardNo());
		
		URI resourceUri = uriBuilder.path("/bbs/regiser").buildAndExpand(board.getBoardNo()).encode().toUri();
		return ResponseEntity.created(resourceUri).build();
	}
}
