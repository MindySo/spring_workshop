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

import com.ssafy.exam.model.dto.Food;
import com.ssafy.exam.model.service.FoodService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/food")
public class FoodController {
	
	private final FoodService foodService;
	public FoodController(FoodService foodService) {
		this.foodService = foodService;
	}
	
	@GetMapping("list")
	public String list(HttpSession session, Model model) throws Exception {
		List<Food> list = foodService.list();
		System.out.println(list);
		model.addAttribute("list", list);
		return "food/list";
	}
	
	@GetMapping("detail")
	public String detail(int no, Model model) throws Exception {
		model.addAttribute(foodService.detail(no));
		return "food/detail";
	}
	
	@GetMapping("delete")
	public String delete(@RequestParam("no") int no) throws SQLException {
		foodService.remove(no);
		return "redirect:list";
	}
	
	@GetMapping("update")
	public String update(@RequestParam("no") int no, Model model) throws SQLException {
		model.addAttribute(foodService.detail(no));
		return "food/update";
	}

	@PostMapping("update")
	public String updateProcess(@ModelAttribute("board") Food food) throws Exception {
		foodService.update(food);
		return "redirect:list";
	}
	
	@GetMapping("write")
	public String write() {
		return "food/write";
	}

	@PostMapping("write")
	public String writeProcess(Food food) throws Exception {		// title, content
		foodService.write(food);
		return "redirect:list";
	}
	
	
}












