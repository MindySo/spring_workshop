package com.ssafy.exam.model.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.exam.model.dto.Board;
import com.ssafy.exam.model.dto.BoardFile;

//@Mapper
public interface BoardDao {
	int insertBoard(Board board) throws SQLException;
	int insertBoardFile(BoardFile boardFile) throws SQLException;
	List<Board> selectAll() throws SQLException;
	List<BoardFile> selectBoardFileList(int no) throws SQLException;
	Board selectBoard(int no) throws SQLException;
	int deleteBoard(int no) throws SQLException;
	void updateBoard(Board board) throws SQLException;
	
}
