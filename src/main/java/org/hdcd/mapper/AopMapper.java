package org.hdcd.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.hdcd.domain.Board;

@Mapper
public interface AopMapper {
	
	public void create(Board board) throws Exception;
}
