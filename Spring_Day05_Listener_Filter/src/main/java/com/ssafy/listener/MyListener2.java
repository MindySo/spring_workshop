package com.ssafy.listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

// web.xml을 이용한 등록
public class MyListener2 implements ServletContextListener {
    public MyListener2() {
    }

    public void contextInitialized(ServletContextEvent sce)  { 
    	System.out.println("웹어플리케이션 시작2");
    	ServletContext context = sce.getServletContext();
    	System.out.println(context.getInitParameter("welcome"));
    }

    public void contextDestroyed(ServletContextEvent sce)  { 
    	System.out.println("웹어플리케이션 종료1");
    }
	
}
