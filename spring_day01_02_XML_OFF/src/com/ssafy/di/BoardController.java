package com.ssafy.di;

public class BoardController {
	private BoardService boardService;

	BoardController (BoardService boardService){
		this.boardService = boardService;
		System.out.println("boardController 호출");
	}
	
	public void writeBoard() {
		System.out.println("BoardController -> writeBoard() 호출");
		boardService.writeBoard();
	}

}
