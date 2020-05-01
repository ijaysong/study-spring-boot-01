package org.hdcd.common.exception;

import java.util.List;

import org.hdcd.exception.BoardRecordNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

// @ControllerAdvice 애너테이션은 스프링 컨트롤러에서 발생하는 예외를 처리하는 핸들러 클래스임을 명시한다
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
	
	private static final Logger logger = LoggerFactory.getLogger(ApiExceptionHandler.class);
	
	// 응답 본문에 오류정보 표시
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
		logger.info("handleExceptionInternal");
		
		ApiErrorInfo apiErrorInfo = new ApiErrorInfo();
		apiErrorInfo.setMessage(ex.toString());
		
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, 
			HttpStatus status, WebRequest request) {
		logger.info("handleMethodArgumentNotValid");
		
		ApiErrorInfo apiErrorInfo = new ApiErrorInfo();
		apiErrorInfo.setMessage(ex.toString());
		
		List<ObjectError> globalErrors = ex.getBindingResult().getGlobalErrors();
		
		for(ObjectError globalError : globalErrors) {
			apiErrorInfo.addDetailInfo(globalError.getObjectName(), globalError.getDefaultMessage());
		}
		
		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
		
		for(FieldError fieldError : fieldErrors) {
			apiErrorInfo.addDetailInfo(fieldError.getField(), fieldError.getDefaultMessage());
		}
		
		return super.handleExceptionInternal(ex, apiErrorInfo, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		logger.info("handleBindException");
		
		ApiErrorInfo apiErrorInfo = new ApiErrorInfo();
		apiErrorInfo.setMessage(ex.toString());
		
		List<ObjectError> globalErrors = ex.getBindingResult().getGlobalErrors();
		
		for(ObjectError globalError : globalErrors) {
			apiErrorInfo.addDetailInfo(globalError.getObjectName(), globalError.getDefaultMessage());
		}
		
		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
		
		for(FieldError fieldError : fieldErrors) {
			apiErrorInfo.addDetailInfo(fieldError.getField(), fieldError.getDefaultMessage());
		}
		
		return super.handleExceptionInternal(ex, apiErrorInfo, headers, status, request);
	}
	
	// 사용자 정의 예외
	// @ExceptioinHandler 애너테이션은 괄호 안에 설정한 예외 타입을 해당 메서드가 처리한다는 것을 의미한다.
	// 메서드 매개변수에 처리해야 하는 예외 클래스를 선언한다
	@ExceptionHandler
	public ResponseEntity<Object> handleBoardRecordNotFoundException(BoardRecordNotFoundException ex, WebRequest request) {
		logger.info("handleBoardRecordNotFoundException");
		
		ApiErrorInfo restError = new ApiErrorInfo();
		restError.setMessage("BoardRecord Not Found");
		
		return super.handleExceptionInternal(ex, restError, null, HttpStatus.NOT_FOUND, request);
	}
	
	// 시스템 예외
	@ExceptionHandler
	public ResponseEntity<Object> handleSystemException(Exception ex, WebRequest request) {
		logger.info("handleSystemException");
		
		ApiErrorInfo restError = new ApiErrorInfo();
		restError.setMessage(ex.toString());
		
		return super.handleExceptionInternal(ex, restError, null, HttpStatus.INTERNAL_SERVER_ERROR, request);
	}
}
