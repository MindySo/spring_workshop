package com.ssafy.exam.model.dao;

import java.util.List;

import com.ssafy.exam.model.dto.Board;
import com.ssafy.exam.model.dto.BoardFile;
import com.ssafy.exam.model.dto.BoardReaction;
import com.ssafy.exam.model.dto.BoardSearch;

//@Mapper
public interface BoardDao {
	int insertBoard(Board board);
	int insertBoardFile(BoardFile boardFile);
	List<Board> selectBoard(BoardSearch boardSearch);
	List<BoardFile> selectBoardFileList(int no);
	Board selectDetail(int boardNo);
	void deleteBoard(int no);
	void deleteBoardFile(int no);
	void updateBoard(Board board);
	void updateViewCnt(int no);
	BoardReaction selectBoardReaction(BoardReaction boardReaction);
	void insertBoardReaction(BoardReaction boardReaction);
	void updateBoardReaction(BoardReaction boardReaction);
	void deleteBoardReaction(BoardReaction boardReaction);
}
