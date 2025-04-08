package com.ssafy.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

//@Component
@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;

	BoardController (){
	}
	
	public void writeBoard() {
		System.out.println("BoardController -> writeBoard() 호출");
		boardService.writeBoard();
	}

}
