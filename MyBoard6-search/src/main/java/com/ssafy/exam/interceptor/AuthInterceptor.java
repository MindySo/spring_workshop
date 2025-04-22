package com.ssafy.exam.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class AuthInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(
			HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		if (session.getAttribute("loginUser") == null) {  // 로그인 상태 아님 
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}
		return true;
	}

//	@Override
//	public void postHandle(
//			HttpServletRequest request, HttpServletResponse response, Object handler,
//			ModelAndView modelAndView) throws Exception {
//		modelAndView.addObject("interceptorMsg", "인터셉터에서 추가한 메세지입니다.");
//	}
//
//	@Override
//	public void afterCompletion(
//			HttpServletRequest request, HttpServletResponse response, Object handler, 
//			Exception ex)
//			throws Exception {
//		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
//	}
	
}






