package com.ssafy.board.test;

import org.apache.ibatis.session.SqlSession;

import com.ssafy.board.config.MybatisConfig;
import com.ssafy.board.model.dao.BoardDao;
import com.ssafy.board.model.dto.Board;

public class MybatisTest {
	public static void main(String[] args) {
//		MybatisConfig.getFactory();

//		try (SqlSession session = MybatisConfig.getFactory().openSession(true)) {
//			BoardDao dao = session.getMapper(BoardDao.class);
//			dao.insertBoard(new Board("새 글", "글쓴이", "새로새로새로새로새로"));
//		}
//
//
		try (SqlSession session = MybatisConfig.getFactory().openSession(true)) {
			BoardDao dao = session.getMapper(BoardDao.class);
			dao.deleteBoard(5);
		}
		
		try (SqlSession session = MybatisConfig.getFactory().openSession(true)) {
			BoardDao dao = session.getMapper(BoardDao.class);
			for (Board board : dao.selectAll()) {
				System.out.println(board);
			}
		}  
		
	}

}
