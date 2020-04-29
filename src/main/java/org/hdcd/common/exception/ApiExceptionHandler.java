package org.hdcd.common.exception;

import org.hdcd.exception.BoardRecordNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

// @ControllerAdvice 애너테이션은 스프링 컨트롤러에서 발생하는 예외를 처리하는 핸들러 클래스임을 명시한다
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
	
	private static final Logger logger = LoggerFactory.getLogger(ApiExceptionHandler.class);

	// @ExceptioinHandler 애너테이션은 괄호 안에 설정한 예외 타입을 해당 메서드가 처리한다는 것을 의미한다.
	// 메서드 매개변수에 처리해야 하는 예외 클래스를 선언한다
	@ExceptionHandler
	public ResponseEntity<Object> handleBoardRecordNotFoundException(BoardRecordNotFoundException ex, WebRequest request) {
		logger.info("handleBoardRecordNotFoundException");
		
		ApiErrorInfo restError = new ApiErrorInfo();
		restError.setMessage("BoardRecord Not Found");
		
		return super.handleExceptionInternal(ex, restError, null, HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler
	public ResponseEntity<Object> handleSystemException(Exception ex, WebRequest request) {
		logger.info("handleSystemException");
		
		ApiErrorInfo restError = new ApiErrorInfo();
		restError.setMessage(ex.toString());
		
		return super.handleExceptionInternal(ex, restError, null, HttpStatus.INTERNAL_SERVER_ERROR, request);
	}
}
