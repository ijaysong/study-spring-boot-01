package org.hdcd.controller;

import org.hdcd.service.ErrorHandlingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ErrorHandling")
public class ErrorHandlingController {
	
	private static final Logger logger = LoggerFactory.getLogger(ErrorHandlingController.class);
	
	@Autowired
	private ErrorHandlingService service;
	
	// 에러가 발생할 상황을 가정한다.
	// Case 1. 등록할 때 제목에 빈 값을 입력하여 유효값 검증 시,예외 발생
	// Case 2. 삭제 할 때 매핑 파일에서 예외 발생
	// Case 3. 존재하지 않는 게시물을 조회할 때 사용자가 정의한 예외 발생
	
	

}
