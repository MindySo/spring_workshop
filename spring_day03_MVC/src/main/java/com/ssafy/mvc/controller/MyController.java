package com.ssafy.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {
	// get, post 관련없이 해당 경로로 들어온다.
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping(value="/home", method = RequestMethod.GET)
	public ModelAndView homeHandle1() {
		// 바구니이자, 페이지 정보를 저장하는 객체
		ModelAndView mav = new ModelAndView();
		
		// 바구니에 데이터를 담음
		mav.addObject("msg", "Welcome to Spring MVC (GET)");
		
		// 페이지 정보 저장
		mav.setViewName("home");
		
		return mav;
	}
	
}
