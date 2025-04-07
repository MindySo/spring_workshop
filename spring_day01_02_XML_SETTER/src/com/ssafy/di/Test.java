package com.ssafy.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Test {
	public static void main(String[] args) {
		// 설정된 xml(applicationContext.xml)의 정보를 Container에게 알려준다.
//		ApplicationContext container = new ClassPathXmlApplicationContext();
//		ApplicationContext container = new FileSystemXmlApplicationContext();
		
		// 위 두 가지 기능 모두 수행 가능
		ApplicationContext container = new GenericXmlApplicationContext("applicationContext.xml");
		
		BoardController controller = (BoardController)container.getBean("boardController");
		controller.writeBoard();
	}
}
