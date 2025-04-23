package com.ssafy.mvc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.mvc.model.dto.User;

@RestController
@RequestMapping("/rest2")
public class RestController2 {
	
	// hello 페이지를 찾으려고 한다.
	@GetMapping("/test1")
	public String test1() {
		return "/";
	}
	
	// @ResponseBody : 텍스트 형태
	@GetMapping("/test2")
	public String test2() {
		return "hello";
	}
	
	// @ResponseBody : map으로 해보자
	@GetMapping("/test3")
	public Map<String , String> test3() {
		Map<String , String> data = new HashMap();
		data.put("id", "ssafy");
		data.put("pw", "1234");
		return data;
	}
	
	// @ResponseBody : map으로 해보자
	@GetMapping("/test4")
	public List<User> test4() {
		List<User> userList = new ArrayList();
		userList.add(new User("ssafy1", "qwer", "김싸피"));
		userList.add(new User("ssafy2", "asdf", "박싸피"));
		return userList;
	}
	
}
