package com.springbook.biz.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.springbook.biz.board.BoardVO;

@Repository
public class BoardDAOSpring {
	
		@Autowired
		private JdbcTemplate jdbcTemplate;
	
		// SQL 명령어
		private final String BOARD_INSERT = "insert into board(seq,title,writer,content)"
				+ "values((select nvl(max(seq),0)+1 from board),?,?,?)";
		private final String BOARD_UPDATE = "update board set title=?, content=? where seq=?";
		private final String BOARD_DELETE = "delete board where seq=?";
		private final String BOARD_GET = "select * from board where seq=?";
		private final String BOARD_LIST = "select * from board order by seq desc";
		
		
		public void insertBoard(BoardVO vo) {
			System.out.println("==> Spring JDBC로 insertBoard()");
			jdbcTemplate.update(BOARD_INSERT,vo.getTitle(),vo.getWriter(),vo.getContent());
		}
		
		public void updateBoard(BoardVO vo) {
			System.out.println("==> Spring JDBC로 updateBoard()");
			jdbcTemplate.update(BOARD_UPDATE,vo.getTitle(),vo.getContent(),vo.getSeq());
		}
		
		public void deleteBoard(BoardVO vo) {
			System.out.println("==> Spring JDBC로 deleteBoard()"); 
			jdbcTemplate.update(BOARD_DELETE,vo.getSeq());
		}
		
		public BoardVO getBoard(BoardVO vo) {
			System.out.println("==> Spring JDBC로 getBoard()"); 
			Object[] args = {vo.getSeq()};
			return jdbcTemplate.queryForObject(BOARD_GET, args, new BoardRowMapper());
		}
		
		public List<BoardVO> getBoardList(BoardVO vo){
			System.out.println("==> Spring JDBC로 getBoardList()"); 
			return jdbcTemplate.query(BOARD_LIST, new BoardRowMapper());
		}
}
