package com.ssafy.mvc.model.service;

import java.util.List;

import com.ssafy.mvc.model.dto.Board;
import com.ssafy.mvc.model.dto.SearchCondition;


public interface BoardService {
	// 게시글 전체 조회
	public List<Board> getBoardList();

	// 게시글 상세조회 (클릭시 읽는거)
	public Board readBoard(int id);
	
	// 게시글 상세조회 (안 읽는거)
	public Board getBoard(int id);

	// 게시글 작성
	public void writeBoard(Board board);

	// 게시글 삭제
	public boolean removeBoard(int id);

	// 게시글 수정
	public void modifyBoard(Board board);

	// 게시글 검색
	public List<Board> search(SearchCondition condition);
}
