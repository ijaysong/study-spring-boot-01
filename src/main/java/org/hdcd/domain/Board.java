package org.hdcd.domain;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;


public class Board implements Serializable{
	
	private static final long serialVersionUID = 7211418002481684234L;
	
	private int boardNo;
	// 입력값의 검사규칙을 지정한다.
	@NotBlank
	private String title;
	private String content;
	private String writer;
	private Date regDate;
	
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", title=" + title + ", content=" + content + ", writer=" + writer + ", regdate=" + regDate + "]";
	}
	
}
