package com.ssafy.exam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ssafy.exam.model.dto.User;

import jakarta.servlet.http.HttpSession;


@Controller
public class MainController {
	@GetMapping("/")
	public String hello() {
		return "hello";
	}
}
