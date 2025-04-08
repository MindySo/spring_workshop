package com.ssafy.proxy01;

import java.util.Random;

public class PersonProxy implements Person {
	// 프록시 객체에 주입할 인간
	private Person person;

	// 설정자 주입
	public void setPerson(Person person) {
		this.person = person;
	}

	@Override
	public void coding() {
		System.out.println("컴퓨터 부팅");

		try {
//				----------- 핵심기능 -------------------------------------
			person.coding();
//				--------------------------------------------------------
			if (new Random().nextBoolean()) {
				throw new OuchException();
			}

			System.out.println("git commit 한다.");

		} catch (OuchException e) {
			e.handleException();
			System.out.println("반차를 낸다");
		} finally {
			System.out.println("침대에 눕는다");
		}
	}
}
