package com.ssafy.exam.model.service;

import java.io.IOException;
import java.util.List;

import com.ssafy.exam.model.dto.Board;
import com.ssafy.exam.model.dto.BoardReaction;
import com.ssafy.exam.model.dto.BoardSearch;

public interface BoardService {
	void write(Board board);
	List<Board> list(BoardSearch boardSearch) ;
	Board detail(int no);
	void delete(int no);
	void update(Board board);
	void viewCntIncrease(int no);
	BoardReaction getReact(BoardReaction boardReaction);
	void newReact(BoardReaction boardReaction);
	void updateReact(BoardReaction boardReaction);
	void deleteReact(BoardReaction boardReaction);
}
