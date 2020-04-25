package org.hdcd.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BoardRecordNotFoundException extends Exception {
	
	public BoardRecordNotFoundException(String msg) {
		super(msg);
	}
}
