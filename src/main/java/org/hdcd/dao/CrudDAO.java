package org.hdcd.dao;
/*
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.hdcd.domain.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;



@Repository*/
public class CrudDAO {
	/*
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void create(Board board) throws Exception {
		String query = "INSERT INTO board (title, content, writer) VALUES (?, ?, ?)";
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(query, new String[] {"boardNo"});
				ps.setString(1, board.getTitle());
				ps.setString(2, board.getContent());
				ps.setString(3, board.getWriter());
				return ps;
			}
 		}, keyHolder);
		board.setBoardNo(keyHolder.getKey().intValue());
	}
	
	public List<Board> list() throws Exception {
		String query = "SELECT board_no, title, content, writer, reg_date "
				+ "FROM board WHERE board_no > 0 ORDER BY board_no DESC, reg_date DESC";
		List<Board> results = jdbcTemplate.query(query, new RowMapper<Board>() {
			
			@Override
			public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
				Board board = new Board();
				
				board.setBoardNo(rs.getInt("board_no"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setWriter(rs.getString("writer"));
				board.setRegDate(new Date(Timestamp.valueOf(rs.getString("reg_date")).getTime()));
				
				return board;
			}
		});
		
		return results;
	}
	
	public Board read(int boardNo) throws Exception {
		String query = "SELECT board_no, title, content, writer, reg_date FROM board WHERE board_no = ?";
		List<Board> results = jdbcTemplate.query(query, new RowMapper<Board>() {

			@Override
			public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
				Board board = new Board();
				
				board.setBoardNo(rs.getInt("board_no"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setWriter(rs.getString("writer"));
				board.setRegDate(new Date (Timestamp.valueOf(rs.getString("reg_date")).getTime()));
				
				return board;
			}
		}, boardNo);
		
		return results.isEmpty() ? null : results.get(0);
	}
	
	public void update(Board board) throws Exception {
		String query = "UPDATE board SET title = ?, content = ? WHERE board_no = ?";
		
		jdbcTemplate.update(query, board.getTitle(), board.getContent(), board.getBoardNo());
	}
	
	public void remove(int boardNo) throws Exception {
		String query = "DELETE FROM board WHERE board_no = ?";
		
		jdbcTemplate.update(query, boardNo);
	}
	*/
}
