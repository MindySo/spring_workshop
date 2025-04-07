package com.ssafy.di;

public class BoardController {
	private BoardService boardService;

	BoardController (){
	}
	
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}
	
	public void writeBoard() {
		System.out.println("BoardController -> writeBoard() 호출");
		boardService.writeBoard();
	}

}
