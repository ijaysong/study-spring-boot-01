package org.hdcd.common.exception;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ApiErrorInfo implements Serializable {

	private static final long serialVersionUID = 3298475736382777210L;
	
	private String message;
	private final List<ApiErrorDetailInfo> details = new ArrayList<ApiErrorDetailInfo>();

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public void addDetailInfo(String target, String message) {
		details.add(new ApiErrorDetailInfo(target, message));
	}
	
	public List<ApiErrorDetailInfo> getDetails() {
		return details;
	}
}
