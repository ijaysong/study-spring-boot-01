package org.hdcd.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Member implements Serializable{
	private static final long serialVersionUID = -3367660229737526956L;
	private String userId;
	private String password;
	private int coin;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date dateOfBirth;
	private Address address;
	private List<Card> cardList;
	
	private int userNo;
	private String userPw;
	private String userName;
	private Date regDate;
	private Date updDate;
	private List<MemberAuth> authList;
	
	
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

	public int getCoin() {
		return coin;
	}

	public void setCoin(int coin) {
		this.coin = coin;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Card> getCardList() {
		return cardList;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public void setCardList(List<Card> cardList) {
		this.cardList = cardList;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Date getUpdDate() {
		return updDate;
	}

	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}

	public List<MemberAuth> getAuthList() {
		return authList;
	}

	public void setAuthList(List<MemberAuth> authList) {
		this.authList = authList;
	}

}
