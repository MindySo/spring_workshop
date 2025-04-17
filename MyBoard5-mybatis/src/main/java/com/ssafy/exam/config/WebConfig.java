package com.ssafy.exam.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafy.exam.interceptor.AuthInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Autowired
	private AuthInterceptor authInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		/*
		 
		 ?  : log? -> log1, loga, ... 
		 *  : 0개 이상의 문자
		    : board* -> boardList, boardWrite, ...
		    : *board* -> board가 포함된 문자열  
		 ** : 0개 이상의 디렉토리와 파일 
		 */
		registry.addInterceptor(authInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns("/", "/user/login");
	}
	
}



