package org.hdcd.controller;

import java.util.List;

import org.hdcd.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/users")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping(value="", method=RequestMethod.POST, produces="text/plain;charset=UTF-8")
	// 입력값 검증을 할 도메인 클래스에 @Validated를 지정한다
	// 도메인 클래스 직후에 BindingResult를 정의한다
	// BindingResult에는 요청 데이터의 바인딩 에러와 입력값 검증 에러 정보가 저장된다
	public ResponseEntity<String> register(@Validated @RequestBody User user, BindingResult result) {
		logger.info("register");
		
		// 입력값 검증 에러가 발생한 경우, true를 반환한다
		logger.info("result.hasError() = " + result.hasErrors());
		
		// 입력값 검증 후 BindingResult가 제공하는 메서드를 이용하여 검사 결과를 확인한다
		if(result.hasErrors()) {
			List<ObjectError> allErrors = result.getAllErrors();
			List<ObjectError> globalErrors = result.getGlobalErrors();
			List<FieldError> fieldErrors = result.getFieldErrors();
			
			logger.info("allErrors.size() = " + allErrors.size());
			logger.info("globalErrors.size() = " + globalErrors.size());
			logger.info("fieldErrors.size() = " + fieldErrors.size());
			
			for(int i = 0; i < allErrors.size(); i++) {
				ObjectError objectError = allErrors.get(i);
				logger.info("allError = " + objectError);
			}
			
			for(int i = 0; i < globalErrors.size(); i++) {
				ObjectError objectError = globalErrors.get(i);
				logger.info("globalError = " + objectError);
			}
			
			for(int i = 0; i < fieldErrors.size(); i++) {
				FieldError fieldError = fieldErrors.get(i);
				logger.info("fieldError = " + fieldError);
				logger.info("fieldError.getDefaultMessage() = " + fieldError.getDefaultMessage());
			}
			
			ResponseEntity<String> entity = new ResponseEntity<String>(result.toString(), HttpStatus.BAD_REQUEST);
			return entity;
		}
		
		logger.info("user.getUserId() = " + user.getUserId()); 
		logger.info("user.getpassword() = " + user.getPassword());
		logger.info("user.getUserName() = " + user.getUserName());
		logger.info("user.getEmail() = " + user.getEmail());
		logger.info("user.getDateOfBirth() = " + user.getDateOfBirth());
		logger.info("user.getGender() = " + user.getGender());
		
		ResponseEntity<String> entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		return entity;
	}
}
