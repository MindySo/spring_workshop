package com.ssafy.mvc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafy.mvc.interceptor.AInterceptor;
import com.ssafy.mvc.interceptor.BInterceptor;
import com.ssafy.mvc.interceptor.CInterceptor;

public class WebConfig implements WebMvcConfigurer{
	
	// 의존성 주입 (생성자, 설정자, 필드)
	@Autowired
	private AInterceptor aInterceptor;
	@Autowired
	private BInterceptor bInterceptor;
	@Autowired
	private CInterceptor cInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(aInterceptor).addPathPatterns("/hello");
		registry.addInterceptor(bInterceptor).addPathPatterns("/hello");
		registry.addInterceptor(cInterceptor).addPathPatterns("/**");
	}
}
