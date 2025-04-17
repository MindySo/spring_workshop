package com.ssafy.exam.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.exam.model.dto.Board;
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
	public String list(HttpSession session, Model model) throws Exception {
		List<Board> list = boardService.list();
		System.out.println(list);
		model.addAttribute("list", list);
		return "board/list";
	}
	
	@GetMapping("detail")
	public String detail(int no, Model model) throws Exception {
		model.addAttribute(boardService.detail(no));
		return "board/detail";
	}
	
	@GetMapping("delete")
	public String delete(@RequestParam("no") int no) throws SQLException {
		boardService.remove(no);
		return "redirect:list";
	}
	
	@GetMapping("update")
	public String update(@RequestParam("no") int no, Model model) throws SQLException {
		model.addAttribute(boardService.detail(no));
		return "board/update";
	}

	@PostMapping("update")
	public String updateProcess(@ModelAttribute("board") Board board) throws Exception {
		boardService.update(board);
		return "redirect:list";
	}
	
	@GetMapping("write")
	public String write() {
		return "board/write";
	}

	@PostMapping("write")
	public String writeProcess(Board board) throws Exception {		// title, content
		boardService.write(board);
		return "redirect:list";
	}
	
	
}












