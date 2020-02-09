package org.hdcd.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

public class User implements Serializable{
	private static final long serialVersionUID = 1710069820531371155L;
	
	// 입력값 검증 규칙을 지정한다
	// 문자열이 null이 아니고 trim한 길이가 0보다 크다는 것을 검사한다
	@NotBlank
	private String userId;
	
	// 문자열이 null이 아니고 trim한 길이가 0보다 크다는 것을 검사한다
	@NotBlank
	private String password;
	
	// 여러 개의 입력값 검증 규칙을 지정 할 수 있다
	// 문자열이 null이 아니고 trim한 길이가 3보다 작은 것을 검사한다
	@NotBlank
	@Size(max = 3)
	private String userName;
	
	// 이메일 주소 형식인지를 검사한다
	@Email
	private String email;
	
	// 과거 날짜인지를 검사한다
	@Past
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dateOfBirth;
	private String gender;
	
	// 중첩된 자바빈즈의 입력값 검증을 지정한다
	@Valid
	private Address address;
	
	// 자바빈즈 컬렉션의 입력값 검증을 지정한다
	@Valid
	private List<Card> cardList;
	
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public List<Card> getCardList() {
		return cardList;
	}
	public void setCardList(List<Card> cardList) {
		this.cardList = cardList;
	}
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
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
}
