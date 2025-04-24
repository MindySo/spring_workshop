package com.ssafy.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ssafy.mvc.model.dto.User;
import com.ssafy.mvc.model.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	
	//서비스객체가 필요해!(의존성 주입)
	@Autowired
	private UserService userService;
	
	//회원가입 진행해보자
	@GetMapping("/signup")
	public String signupForm() {
		return "/user/signupform";
	}
	
	@PostMapping("/signup")
	public String signup(@ModelAttribute User user) {
		userService.signup(user);
		//잘등록이 되었다!
		//그러면 요기서 로그인 처리 해주고... -> 메인페이지 이동도 좋아!
		return "redirect:login";
	}
	
	
	@GetMapping("/login")
	public String loginForm() {
		return "/user/loginform";
	}
	
	
	@PostMapping("/login")
	public String login(@ModelAttribute User user, HttpSession session) {
		User tmp = userService.login(user.getId(), user.getPassword());	
		if(tmp == null) {
			//로그인 실패 -> 로그인 페이지에 머물러있어 
			return "redirect:login";
		}
		//로그인 성공 -> 세션에다가 저장하고 -> list 화면으로 넘어가자
		session.setAttribute("loginUser", tmp.getName());
		return "redirect:list";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:login";
	}
	
	@GetMapping("/users")
	public String userList(Model model) {
		model.addAttribute("userList", userService.getUserList());
		return "/user/adminPage";
	}
	
	
}
