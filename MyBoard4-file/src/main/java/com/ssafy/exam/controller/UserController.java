package com.ssafy.exam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.exam.model.dto.User;
import com.ssafy.exam.model.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private final UserService userService;
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	/**
	 * 로그인 폼 페이지 이동하기
	 * @return
	 */
	@GetMapping("login")
	public String login() {
		return "user/login";
	}

	@PostMapping("login")
	public String loginProcess(User user, HttpSession session) throws Exception {
		System.out.println(user);
		User loginUser = userService.login(user);
		if (loginUser != null) {  // 로그인 성공
			session.setAttribute("loginUser", loginUser);
		}
		return "redirect:/";
	}
	
	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
}










