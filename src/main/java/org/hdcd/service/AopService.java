package org.hdcd.service;

import java.util.List;

import org.hdcd.domain.Board;

public interface AopService {
	
	public void register(Board board) throws Exception;
	public List<Board> list() throws Exception;
	public Board read(int boardNo) throws Exception;
	public void modify(Board board) throws Exception;
	public void remove(int boardNo) throws Exception;
}
