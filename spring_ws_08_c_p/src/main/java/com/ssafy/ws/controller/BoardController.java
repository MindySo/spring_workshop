package com.ssafy.ws.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ws.model.dto.Board;
import com.ssafy.ws.model.dto.SearchCondition;
import com.ssafy.ws.service.BoardService;


/*
 * 게시판 기능을 위한 컨트롤러
 * 게시글 목록 조회, 게시글 상세 조회, 게시글 작성, 게시글 수정, 게시글 삭제 기능을 제공
 */

// BoardController 클래스를 컨트롤러 Bean으로 등록하고, 정의된 모든 요청 URL은 "/board"로 시작하도록 매핑
@RestController
@RequestMapping("/board")
public class BoardController {
	
	private final BoardService boardService;
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	
    /*
     *  Q1. 게시글 전체 목록 조회 (GET /board)
     */
	@GetMapping("/board")
	public List<Board> list(SearchCondition searchCondition) {
		return boardService.getBoardList(searchCondition);
	}

	/*
	 * Q2. 게시글 상세 조회 (GET /board/{id})
	 */
	@GetMapping("/board/{id}")
	public Board detail(@PathVariable("id") int id) {
		return boardService.readBoard(id);
	}
    
    /*
     * Q3. 게시글 생성 (POST /board)
     */
	@PostMapping("/board")
	public int detail(Board board) {
		return boardService.writeBoard(board);
	}

    /*
     * Q4. 게시글 수정 (PUT /board/{id})
     */
	@PutMapping("/board")
	public int modify(Board board) {
		return boardService.modifyBoard(board);
	}

	/*
	 * Q5. 게시글 삭제 (DELETE /board/{id})
	 */
	@DeleteMapping("/board/{id}")
	public int modify(@PathVariable("id") int id) {
		return boardService.removeBoard(id);
	}
}