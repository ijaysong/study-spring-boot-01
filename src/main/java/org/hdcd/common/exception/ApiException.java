package org.hdcd.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

// ResponseEntityExceptioinHandler를 상속받고 @ControllerAdvice를 붙인다
// ResponseEntityExceptioinHandler는 Rest API용 예외 처리 클래스를 만들 때 도움이 되는 클래스이다
// @ControllerAdvice 애너테이셔는 스프링 컨트롤러에서 발생하는 예외를 처리하는 핸들러 클래스임을 명시한다
@ControllerAdvice
public class ApiException extends ResponseEntityExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(ApiException.class);
	
	// 응답 본문에 오류 정보를 표시하려면, handleExceptionInternal 메서드를 재정의해야 한다
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, 
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		logger.info("handleExceptionInternal");
		
		ApiErrorInfo apiErrorInfo = new ApiErrorInfo();
		apiErrorInfo.setMessage(ex.toString());
		
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
}
