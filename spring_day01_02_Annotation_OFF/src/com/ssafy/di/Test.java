package com.ssafy.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Test {
	public static void main(String[] args) {
		ApplicationContext container = new GenericXmlApplicationContext("applicationContext.xml");
		
		BoardController controller = (BoardController)container.getBean("boardController");
		controller.writeBoard();
	}
}
