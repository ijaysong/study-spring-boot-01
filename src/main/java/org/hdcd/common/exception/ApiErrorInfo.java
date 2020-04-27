package org.hdcd.common.exception;

import java.io.Serializable;

public class ApiErrorInfo implements Serializable {

	private static final long serialVersionUID = 3298475736382777210L;
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
