package org.hdcd.service;

import java.util.List;

import org.hdcd.domain.Board;

public interface CrudService {
	// JdbcTemplate 활용
	public void register(Board board) throws Exception;
	public List<Board> list() throws Exception;
	public Board read(int boardNo) throws Exception;
	public void modify(Board board) throws Exception;
	public void remove(int boardNo) throws Exception;
	
	// JPA(Java Persistence API) 활용
	public void register2(Board board) throws Exception;
	public List<Board> list2() throws Exception;
	public Board read2(int boardNo) throws Exception;
	public void modify2(Board board) throws Exception;
	public void remove2(int boardNo) throws Exception;
}
