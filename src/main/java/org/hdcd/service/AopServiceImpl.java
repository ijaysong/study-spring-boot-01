package org.hdcd.service;

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

}
