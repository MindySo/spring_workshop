package com.ssafy.mvc.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// HandlerInterceptor
public class AInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// boolean 반환 : true일 때 진행
		
		System.out.println("A : Pre");
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		// 컨트롤러 찍고 온 상태 : modelandview 객체가 있음
		// 예외 발생시 미실행
		
		System.out.println("A : Post");
	}
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		// View가 전달된 후 실행(finally)
		// 예외 발생에 ex 객체가 담겨있음, 아니면 null
		
		System.out.println("A : After");
	}
}
