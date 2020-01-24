package org.hdcd.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Card {
	private String no;
	
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
