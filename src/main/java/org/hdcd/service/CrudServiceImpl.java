package org.hdcd.service;

import java.util.List;

import org.hdcd.dao.CrudDAO;
import org.hdcd.domain.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CrudServiceImpl implements CrudService{
	
	@Autowired
	private CrudDAO dao;
	
	@Override
	public void register(Board board) throws Exception {
		dao.create(board);
	}
	
	@Override
	public List<Board> list() throws Exception {
		return dao.list();
	}

}
