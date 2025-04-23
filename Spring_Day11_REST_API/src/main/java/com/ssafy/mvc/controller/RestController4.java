package com.ssafy.mvc.controller;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest5")
public class RestController4 {
	
	@PostMapping("/board1")
	public User test1(@ModelAttribute User user) {
		return user;
	}
	
	@PutMapping("/test")
	public String test3() {
		return "PUT";
	}
	
	@DeleteMapping("/test")
	public String test4() {
		return "DELETE";
	}
	
}
