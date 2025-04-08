package com.ssafy.aop;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class Programmer implements Person {

	@Override
	public int coding() throws OuchException {
	    System.out.println("열심히 코드를 작성");
	    if (new Random().nextBoolean()) {
	        throw new OuchException();
	    }
	    return (int) ((Math.random() * 10) + 1);
	}
}
