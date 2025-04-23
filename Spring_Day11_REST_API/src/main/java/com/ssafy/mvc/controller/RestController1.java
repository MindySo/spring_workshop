package com.ssafy.mvc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssafy.mvc.model.dto.User;

@Controller
public class RestController1 {
	
	// hello 페이지를 찾으려고 한다.
	@GetMapping("/rest1/test1")
	public String test1() {
		return "hello";
	}
	
	// @ResponseBody : 텍스트 형태
	@GetMapping("/rest1/test2")
	@ResponseBody
	public String test2() {
		return "hello";
	}
	
	// @ResponseBody : map으로 해보자
	@GetMapping("/rest1/test3")
	@ResponseBody
	public Map<String , String> test3() {
		Map<String , String> data = new HashMap();
		data.put("id", "ssafy");
		data.put("pw", "1234");
		return data;
	}
	
	// @ResponseBody : map으로 해보자
	@GetMapping("/rest1/test4")
	@ResponseBody
	public List<User> test4() {
		List<User> userList = new ArrayList();
		userList.add(new User("ssafy1", "qwer", "김싸피"));
		userList.add(new User("ssafy2", "asdf", "박싸피"));
		return userList;
	}
	
}
