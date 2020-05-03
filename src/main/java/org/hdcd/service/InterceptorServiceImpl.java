package org.hdcd.service;

import java.util.List;

import org.hdcd.domain.Board;
import org.hdcd.mapper.InterceptorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InterceptorServiceImpl implements InterceptorService {

	@Autowired
	private InterceptorMapper mapper;
	
	@Override
	public void register(Board board) throws Exception {
		mapper.create(board);
	}

	@Override
	public Board read(int boardNo) throws Exception {
		return mapper.read(boardNo);
	}

	@Override
	public void modify(Board board) throws Exception {
		mapper.update(board);
	}

	@Override
	public List<Board> list() throws Exception {
		return mapper.list();
	}

	@Override
	public void remove(int boardNo) throws Exception {
		mapper.delete(boardNo);
	}

}
