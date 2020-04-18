package org.hdcd.service;

import java.util.List;

import org.hdcd.domain.Board;
import org.hdcd.mapper.AopMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AopServiceImpl implements AopService {
	
	private static final Logger logger = LoggerFactory.getLogger(AopServiceImpl.class);
	
	@Autowired
	private AopMapper mapper;

	@Override
	public void register(Board board) throws Exception {
		logger.info("register");

		mapper.create(board);
	}

	@Override
	public List<Board> list() throws Exception {
		logger.info("list");
		
		return mapper.list();
	}

	@Override
	public Board read(int boardNo) throws Exception {
		logger.info("read");
		
		return mapper.read(boardNo);
	}

	@Override
	public void modify(Board board) throws Exception {
		logger.info("modify");
		
		mapper.modify(board);
	}

	@Override
	public void remove(int boardNo) throws Exception {
		logger.info("remove");
		
		mapper.remove(boardNo);
	}

}
