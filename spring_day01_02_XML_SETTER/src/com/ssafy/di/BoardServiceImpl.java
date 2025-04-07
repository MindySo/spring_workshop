package com.ssafy.di;

public class BoardServiceImpl implements BoardService {
	private BoardDao boardDao;

	public BoardServiceImpl() {
	}

	public void setBoardDao(BoardDao boardDao) {
		this.boardDao = boardDao;
	}
	
	@Override
	public void writeBoard() {
		System.out.println("BoardService -> writeBoard() 호출");
		boardDao.insertBoard();
	}
}
