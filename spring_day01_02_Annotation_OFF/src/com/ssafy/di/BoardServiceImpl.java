package com.ssafy.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Component(value = "boardService")
@Service(value = "boardService")
public class BoardServiceImpl implements BoardService {
	@Autowired
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
