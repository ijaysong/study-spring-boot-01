package org.hdcd.service;

import java.util.List;

//import org.hdcd.dao.CrudDAO;
import org.hdcd.domain.Board;
import org.hdcd.mapper.BoardMapper;
//import org.hdcd.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CrudServiceImpl implements CrudService {
	//스프링부트 의존성 변경의 이유로 커맨드아웃 함 
	//@Autowired
	//private CrudDAO dao;
	
	//@Autowired
	//private BoardRepository repository;
	
	@Autowired
	private BoardMapper mapper;
	
	// JdbcTemplate 활용
	@Override
	public void register(Board board) throws Exception {
		//dao.create(board);
	}
	
	@Override
	public List<Board> list() throws Exception {
		//return dao.list();
		return null;
	}

	@Override
	public Board read(int boardNo) throws Exception {
		//return dao.read(boardNo);
		return null;
	}

	@Override
	public void modify(Board board) throws Exception {
		//dao.update(board);
	}

	@Override
	public void remove(int boardNo) throws Exception {
		//dao.remove(boardNo);
	}

	// JPA(Java Persistence API) 활용
	@Override
	//@Transactional
	public void register2(Board board) throws Exception {
		//repository.save(board);
	}

	@Override
	//@Transactional
	public List<Board> list2() throws Exception {
		//return repository.findAll();
		return null;
	}

	@Override
	//@Transactional
	public Board read2(int boardNo) throws Exception {
		//return repository.getOne(boardNo);
		return null;
	}

	@Override
	//@Transactional
	public void modify2(Board board) throws Exception {
		//Board boardEntity = repository.getOne(board.getBoardNo());
		//boardEntity.setTitle(board.getTitle());
		//boardEntity.setContent(board.getContent());
		//boardEntity.setWriter(board.getWriter());
	}

	@Override
	//@Transactional
	public void remove2(int boardNo) throws Exception {
		//repository.deleteById(boardNo);
	}
	

	// Mybatis 활용
	@Override
	public void register3(Board board) throws Exception {
		mapper.create(board);
	}

	@Override
	public List<Board> list3() throws Exception {
		return mapper.list();
	}

	@Override
	public Board read3(int boardNo) throws Exception {
		return mapper.read(boardNo);
	}

	@Override
	public void modify3(Board board) throws Exception {
		mapper.update(board);
	}

	@Override
	public void delete3(int boardNo) throws Exception {
		mapper.delete(boardNo);
	}
}
