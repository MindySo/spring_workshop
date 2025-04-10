package com.ssafy.exam.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.exam.model.dto.User;
import com.ssafy.exam.model.service.UserService;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/")
public class UserController {
	private final UserService userservice;
	
	public UserController(UserService userservice) {
		this.userservice = userservice;
	}

	@GetMapping("/login")
	public String loginForm() {
		return "/user/loginForm";
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute User user, HttpSession httpSession) {
		httpSession.setAttribute("loginUser", user);
		System.out.println(user);
		
		return "redirect:";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession httpSession) {
		httpSession.invalidate();
		
		return "redirect:";
	}
	
	@GetMapping("/signIn")
	public String signInForm() {
		return "/user/signInForm";
	}
	
	@PostMapping("/signIn")
	public String signIn(@ModelAttribute User user) throws Exception {
		userservice.signIn(user);
		
		return "redirect:";
	}
	
	
	
}
