package com.ssafy.exam.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.exam.model.dto.Board;
import com.ssafy.exam.model.dto.BoardReaction;
import com.ssafy.exam.model.dto.BoardSearch;
import com.ssafy.exam.model.dto.User;
import com.ssafy.exam.model.service.BoardService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	private final BoardService boardService;
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@GetMapping("list")
//	public ModelAndView list(HttpSession session, ModelAndView mav) throws Exception {
//		List<Board> list = boardService.list();
//		System.out.println(list);
//		mav.setViewName("board/list");
//		mav.addObject("list", list);
//		return mav;
//	}
	public String list(BoardSearch boardSearch, Model model) throws Exception {
		List<Board> list = boardService.list(boardSearch);
		model.addAttribute("list", list);
		return "board/list";
	}
	
	@GetMapping("detail")
	public String detail(int no, Model model, HttpSession session) throws Exception {
		BoardReaction boardReaction = new BoardReaction();
		boardReaction.setNo(no);
		
		User user = (User) session.getAttribute("loginUser");
		if(user != null) {
			boardReaction.setUserNo(user.getUserNo());
		}
		boardService.viewCntIncrease(no);
		model.addAttribute(boardService.detail(no));
		
		boardReaction = boardService.getReact(boardReaction);
		if(boardReaction != null) {
			model.addAttribute(boardService.getReact(boardReaction));
		}
		return "board/detail";
	}
	
	@GetMapping("delete")
	public String delete(int no) {
		
		boardService.delete(no);
		return "redirect:list";
	}
	
	@GetMapping("update")
	public String update(int no, Model model) {
		model.addAttribute(boardService.detail(no));
		return "board/update";
	}

	@PostMapping("update")
	public String updateProcess(Board board, Model model) {
		boardService.update(board);
		return "redirect:detail?no=" + board.getNo();
	}
	
	@GetMapping("write")
	public String write() {
		return "board/write";
	}

	@PostMapping("write")
	public String writeProcess(Board board) throws Exception {		// title, content
		System.out.println(board);
		boardService.write(board);
		return "redirect:list";
	}
	
	@GetMapping("newReact")
	public String newReact(int no, boolean reaction, HttpSession session) {
		User user = (User) session.getAttribute("loginUser");
		BoardReaction boardReaction = new BoardReaction();
		boardReaction.setNo(no);
		boardReaction.setReaction(reaction);
		boardReaction.setUserNo(user.getUserNo());
		boardService.newReact(boardReaction);
		return "redirect:detail?no=" + no;
	}
	
	@GetMapping("updateReact")
	public String updateReact(int no, boolean reaction, HttpSession session) {
		User user = (User) session.getAttribute("loginUser");
		BoardReaction boardReaction = new BoardReaction();
		boardReaction.setNo(no);
		boardReaction.setReaction(reaction);
		boardReaction.setUserNo(user.getUserNo());
		boardService.updateReact(boardReaction);
		return "redirect:detail?no=" + no;
	}
	
	@GetMapping("deleteReact")
	public String deleteReact(int no, boolean reaction, HttpSession session) {
		User user = (User) session.getAttribute("loginUser");
		BoardReaction boardReaction = new BoardReaction();
		boardReaction.setNo(no);
		boardReaction.setReaction(reaction);
		boardReaction.setUserNo(user.getUserNo());
		boardService.deleteReact(boardReaction);
		return "redirect:detail?no=" + no;
	}
	
}












