package com.ssafy.mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringDay04HelloBootApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringDay04HelloBootApplication.class, args);
		
//		for(String bean : context.getBeanDefinitionNames()) {
//			System.out.println(bean);
//		}
	}

}
