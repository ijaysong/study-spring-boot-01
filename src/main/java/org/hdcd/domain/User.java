package org.hdcd.domain;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class User implements Serializable{
	private static final long serialVersionUID = 1710069820531371155L;
	
	// 입력값 검증 규칙을 지정한다
	@NotBlank
	private String userId;
	private String password;
	
	// 여러 개의 입력값 검증 규칙을 지정 할 수 있다
	@NotBlank
	@Size(max = 3)
	private String userName;
	
	private String email;
	private String birthDay;
	private String gender;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
}
