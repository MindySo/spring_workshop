package com.ssafy.exam.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafy.exam.interceptor.AuthInterceptor;

@Configuration
@MapperScan(basePackages = "com.ssafy.exam.model.dao")
public class DBConfig {
	
	
	
}



