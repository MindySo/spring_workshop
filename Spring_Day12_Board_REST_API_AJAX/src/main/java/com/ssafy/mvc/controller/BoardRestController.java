package com.ssafy.mvc.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.mvc.model.dto.Board;
import com.ssafy.mvc.model.dto.SearchCondition;
import com.ssafy.mvc.model.service.BoardService;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api")
@Tag(name= "Board RESTful API", description="crud 기능")
////////////////// CORS 허용 (어노테이션 / 헤더에 직접 추가) //////////////////////////
////////////////// or WebConfig 에서도 허용 가능 //////////////////////////////////
@CrossOrigin("*")	// 전체 메서드에서 CORS 허용
public class BoardRestController {
	// 서비스 의존성 주입
	private final BoardService boardService;

	// 생성자 주입
	public BoardRestController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	////////////////// CORS 허용 (어노테이션 / 헤더에 직접 추가) //////////////////////////
	// 게시글 전체 조회
	@GetMapping("/board")
//	@CrossOrigin(value="*", methods = RequestMethod.GET)	// 1. 어노테이션으로도 허용 가능
	public ResponseEntity<List<Board>> list() {
		List<Board> list = boardService.getBoardList();
		
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("Access-Control-Allow-Origin", "*");	// 2. 헤더에 "Access-Control-Allow-Origin"를 포함하여 CORS 요청 허용
//		return new ResponseEntity<>(list, headers, HttpStatus.OK);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	
	// 검색
//	@GetMapping("/board")
//	@Operation(summary = "게시글 조회 및 검색", description = "조건에 따른 검색을 수행")
//	public ResponseEntity<?> list(@ModelAttribute SearchCondition searchCondition) {
//		List<Board> list = boardService.search(searchCondition);
//		
//		if(list == null || list.size() == 0) {
//			return new ResponseEntity<List<Board>>(list, HttpStatus.NO_CONTENT);	
//		}
//		
//		return new ResponseEntity<List<Board>>(list, HttpStatus.OK);	
//	}
	
	// 상세보기
	@GetMapping("/board/{id}")
	public ResponseEntity<Board> detail(@PathVariable("id") int id) {
		Board board = boardService.readBoard(id);
		
		if(board != null) {
			return ResponseEntity.ok(board);	
		}
		
		// board가 null이면 url 직접 입력해서 들어옴
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	// 게시글 등록(Form 데이터)
	@PostMapping("/board")
	public ResponseEntity<Void> write(@ModelAttribute Board board) {
		boardService.writeBoard(board);
		System.out.println(board);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	// 게시글 삭제
	@Hidden	// api문서(swagger에서 삭제)
	@DeleteMapping("/board/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") int id){
		boolean isDeleted = boardService.removeBoard(id);
		if(isDeleted) {
			return ResponseEntity.status(HttpStatus.OK).body("Board Deleted");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Board Delete Failed");
	}
	
	// 게시글 수정
	@PutMapping("/board/{id}")
	public ResponseEntity<Void> update(@PathVariable("id") int id, @RequestBody Board board){
		board.setId(id);
		boardService.modifyBoard(board);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}
