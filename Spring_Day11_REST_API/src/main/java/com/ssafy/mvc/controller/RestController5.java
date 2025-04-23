package com.ssafy.mvc.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest4")
public class RestController5 {
	
	@GetMapping("/board/{id}")
	public String test1(@PathVariable("id") int id) {
		return id + " : board";
	}
	
	@PostMapping("/test")
	public String test2() {
		return "GET";
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
