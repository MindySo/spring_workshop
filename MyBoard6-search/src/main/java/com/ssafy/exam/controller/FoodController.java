package com.ssafy.exam.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.exam.model.dto.Food;
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
	public String getFoods(@RequestParam String name, Model model) throws IOException {
		model.addAttribute("foods", foodService.getFoodList(name));
		return "food/foodList";
	}
	
}












