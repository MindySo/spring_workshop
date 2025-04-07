package com.ssafy.di;

public class BoardServiceImpl implements BoardService {
	private BoardDao boardDao;
	
//	public BoardServiceImpl() {
//		System.out.println("BoardServiceImpl 생성자 호출");
//	}
	
	public BoardServiceImpl(BoardDao boardDao) {
		System.out.println("BoardServiceImpl 생성자 호출");
		this.boardDao = boardDao;
	}
	
	@Override
	public void writeBoard() {
		System.out.println("BoardService -> writeBoard() 호출");
		boardDao.insertBoard();
	}
}
