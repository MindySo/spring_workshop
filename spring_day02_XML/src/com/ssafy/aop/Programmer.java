package com.ssafy.aop;

import java.util.Random;

public class Programmer implements Person {

	@Override
	public int coding() throws OuchException {
		// 핵심 기능만 작성
		System.out.println("열심히 코드를 작성");
		
		if(new Random().nextBoolean()) {
			throw new OuchException();
		}
		return (int)(Math.random() * 10) + 1;
	}
}
