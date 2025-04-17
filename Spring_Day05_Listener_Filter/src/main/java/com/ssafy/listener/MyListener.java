package com.ssafy.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

// 어노테이션을 이용한 등록
@WebListener
public class MyListener implements ServletContextListener {
    public MyListener() {
    }

    public void contextInitialized(ServletContextEvent sce)  { 
    	System.out.println("웹어플리케이션 시작1");
    }

    public void contextDestroyed(ServletContextEvent sce)  { 
    	System.out.println("웹어플리케이션 종료1");
    }
	
}
