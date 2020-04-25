package org.hdcd.service;

import java.util.List;

import org.hdcd.domain.Board;
import org.hdcd.exception.BoardRecordNotFoundException;
import org.hdcd.mapper.ErrorHandlingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ErrorHandlingServiceImpl implements ErrorHandlingService {

	@Autowired
	private ErrorHandlingMapper mapper;
	
	@Override
	public void register(Board board) throws Exception {
		mapper.create(board);
	}

	@Override
	public Board read(int boardNo) throws Exception {
		Board board = mapper.read(boardNo);
		
		// 게시판의 글이 존재하지 않으면, 사용가자 정의한 예외를 발생시킨다.
		if(board == null) {
			throw new BoardRecordNotFoundException("Not Found boardNo = " + boardNo); 
		}
		return null;
	}

	@Override
	public void modify(Board board) throws Exception {
		mapper.update(board);
	}

	@Override
	public void remove(int boardNo) throws Exception {
		mapper.delete(boardNo);
	}

	@Override
	public List<Board> list() throws Exception {
		return mapper.list();
	}


}
