package com.ssafy.exam.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.exam.model.dto.Board;
import com.ssafy.exam.model.dto.BoardFile;

public interface BoardDao {
	int insertBoard(Board board) throws SQLException;
	int insertBoardFile(BoardFile boardFile) throws SQLException;
	List<Board> selectBoard() throws SQLException;
	List<BoardFile> selectBoardFileList(int no) throws SQLException;
	Board selectDetail(int boardNo) throws SQLException;
	
}
