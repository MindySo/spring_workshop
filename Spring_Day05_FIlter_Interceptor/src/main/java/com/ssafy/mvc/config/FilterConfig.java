package com.ssafy.mvc.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;

import com.ssafy.mvc.filter.MyFilter;

// 설정 모음
@Configuration
public class FilterConfig {
	public FilterRegistrationBean<MyFilter> myfilter(){
		FilterRegistrationBean<MyFilter> registrationBean = new FilterRegistrationBean<>();
		
		registrationBean.setFilter(new MyFilter());	// 어떤 필터
		registrationBean.addUrlPatterns("/hello");	// 경로 작성
		registrationBean.setOrder(1);				// 순서 작성
		
		return registrationBean;
	}
}
