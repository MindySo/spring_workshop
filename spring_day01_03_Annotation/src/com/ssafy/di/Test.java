package com.ssafy.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Test {
    public static void main(String[] args) {

        // 스프링 설정 파일 읽고 컨테이너 생성
        ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");

        // desktop bean 가져오기
        Desktop desktop = (Desktop) context.getBean("desktop");
        Desktop desktop2 = context.getBean("desktop", Desktop.class);

        // programmer bean 가져오기
        Programmer p = context.getBean("programmer", Programmer.class);
        p.setComputer(desktop);
        p.coding();

        System.out.println(desktop == desktop2); // 싱글톤 여부 확인
    }
}
