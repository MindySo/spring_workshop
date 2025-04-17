package com.ssafy.exam.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.exam.model.dto.Board;

public interface BoardService {
	void write(Board board) throws Exception;
	List<Board> list() throws Exception;
	Board detail(int no) throws SQLException;
}
