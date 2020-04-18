package org.hdcd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.hdcd.domain.Board;

@Mapper
public interface AopMapper {
	
	public void create(Board board) throws Exception;
	public List<Board> list() throws Exception;
	public Board read(int boardNo) throws Exception;
	public void modify(Board board) throws Exception;
	public void remove(int boardNo) throws Exception;
}
