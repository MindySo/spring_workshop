package com.ssafy.aop;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class SSAFY implements Person {

	@Override
	public int coding() throws OuchException {
		// 핵심 기능만 작성
		System.out.println("열심히 공부를 한다");
		
		if(new Random().nextBoolean()) {
			throw new OuchException();
		}
		return (int)(Math.random() * 10) + 1;
	}
}
