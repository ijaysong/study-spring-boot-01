package org.hdcd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.hdcd.domain.Board;

@Mapper
public interface InterceptorMapper {
	
	public void create(Board board) throws Exception;
	public Board read(int boardNo) throws Exception;
	public void update(Board board) throws Exception;
	public void delete(int boardNo) throws Exception;
	public List<Board> list() throws Exception;
}
