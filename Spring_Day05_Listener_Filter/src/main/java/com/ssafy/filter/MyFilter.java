package com.ssafy.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import java.io.IOException;

public class MyFilter extends HttpFilter implements Filter {

	public MyFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("서블릿 가기 전");
		
		//////////////////////////////////
		// 서블릿으로 보내는 코드
		// ~~~~~~
		
		// 더 이상 보낼 게 없으면
		chain.doFilter(request, response);
		//////////////////////////////////

		System.out.println("서블릿 다녀온 후");
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
