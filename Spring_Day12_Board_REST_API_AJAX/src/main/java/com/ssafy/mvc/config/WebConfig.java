package com.ssafy.mvc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafy.mvc.interceptor.AdminInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Autowired
	AdminInterceptor adminInterceptor;
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(adminInterceptor).addPathPatterns("/users");
	}

/////////////////// 서버 측에서 CORS 허용 ///////////////////////////////
//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//		// GET, POST 요청에 대해서 CORS 허용
//		registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST");
//	}
	
}
