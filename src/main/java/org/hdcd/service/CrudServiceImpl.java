package org.hdcd.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hdcd.dao.CrudDAO;
import org.hdcd.domain.Board;
import org.hdcd.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CrudServiceImpl implements CrudService{
	
	@Autowired
	private CrudDAO dao;
	
	@Autowired
	private BoardRepository repository;
	
	// JdbcTemplate 활용
	@Override
	public void register(Board board) throws Exception {
		dao.create(board);
	}
	
	@Override
	public List<Board> list() throws Exception {
		return dao.list();
	}

	@Override
	public Board read(int boardNo) throws Exception {
		return dao.read(boardNo);
	}

	@Override
	public void modify(Board board) throws Exception {
		dao.update(board);
	}

	@Override
	public void remove(int boardNo) throws Exception {
		dao.remove(boardNo);
	}

	// JPA(Java Persistence API) 활용
	@Override
	@Transactional
	public void register2(Board board) throws Exception {
		repository.save(board);
	}

	@Override
	@Transactional
	public List<Board> list2() throws Exception {
		return repository.findAll();
	}

	@Override
	@Transactional
	public Board read2(int boardNo) throws Exception {
		return repository.getOne(boardNo);
	}

	@Override
	@Transactional
	public void modify2(Board board) throws Exception {
		Board boardEntity = repository.getOne(board.getBoardNo());
		boardEntity.setTitle(board.getTitle());
		boardEntity.setContent(board.getContent());
		boardEntity.setWriter(board.getWriter());
	}

	@Override
	@Transactional
	public void remove2(int boardNo) throws Exception {
		repository.deleteById(boardNo);
	}
}
