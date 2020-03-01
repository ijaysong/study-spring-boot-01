package org.hdcd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.hdcd.domain.Board;

@Mapper
public interface BoardMapper {
	
	// Statement ID와 같은 이름을 지정한다
	public void create(Board board) throws Exception;
	public Board read(int boardNo) throws Exception;
	public void update(Board board) throws Exception;
	public void delete(int boardNo) throws Exception;
	public List<Board> list() throws Exception;
	public List<Board> search(@Param("title") String title) throws Exception;
}
