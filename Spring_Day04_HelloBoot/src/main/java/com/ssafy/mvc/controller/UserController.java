package com.ssafy.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ssafy.mvc.dto.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	
	@GetMapping("/login")
	public String loginForm() {
		return "/user/loginForm";
	}
	
//	@PostMapping("/login")
//	public String login(@RequestParam("id") String id, @RequestParam("pw") String pw) {
//		
//	}
	
	// 로그인 정보는 세션에 저장하기
	@PostMapping("/login")
	public String login(@ModelAttribute User user, HttpSession session) {
		System.out.println(user);
//		userService.login(user); // 정석
		
		session.setAttribute("loginUser", user.getId());
		return "redirect:hello";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		// 로그아웃 방법
		
		// 1. 세션에서 로그인 정보만 삭제
		session.removeAttribute("loginUser");

		// 2. 세션 자체를 초기화
		session.invalidate();
		
		return "redirect:hello";
	}
	
}
