package com.ssafy.exam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.exam.model.service.FoodService;
import com.ssafy.exam.model.service.UserService;

@Controller
@RequestMapping("/food")
public class FoodController {
	
	private final FoodService foodService;
	public FoodController(FoodService foodService, UserService userService) {
		this.foodService = foodService;
	}
	
	@GetMapping
	public String getFoods(@RequestParam String name, Model model) throws Exception {
		model.addAttribute("foods", foodService.getFoodList(name));
		return "food/foodList";
	}
	
	@GetMapping("/allergic")
	public String getMethodName() throws Exception {
		foodService.getAllergic();
		return "board/list";
	}
	
	
}












