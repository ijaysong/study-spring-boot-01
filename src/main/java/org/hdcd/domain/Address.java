package org.hdcd.domain;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

public class Address implements Serializable {
	// 문자열이 null이 아이고, trim한 길이가 0보다 크다는 것을 검사한다
	@NotBlank
	private String postCode;
	
	// 문자열이 null이 아이고, trim한 길이가 0보다 크다는 것을 검사한다
	@NotBlank
	private String location;

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	
}
