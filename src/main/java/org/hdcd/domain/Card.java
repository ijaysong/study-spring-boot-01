package org.hdcd.domain;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Card implements Serializable {
	// 문자열이 null이 아니고 trim한 길이가 0보다 크다는 것을 검사한다
	@NotBlank
	private String no;
	
	// 미래 날짜인지를 검사한다
	@Future
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date validMonth;

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public Date getValidMonth() {
		return validMonth;
	}

	public void setValidMonth(Date validMonth) {
		this.validMonth = validMonth;
	}
	
	
}
