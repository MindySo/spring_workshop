package com.ssafy.aop;

import org.aspectj.lang.ProceedingJoinPoint;

// aspect : 공통 관심사항을 저장할 클래스(단위)
public class MyAspect {
	// 메서드명은 상관 없다.(현재는 직관적으로 명명)
	
	public void before() {
		System.out.println("컴퓨터를 부팅한다");
	}
	
	public void afterReturning(int num) {
		System.out.println(num + "시간을 투자해 git에 commit한다");
	}
	
	public void afterThrowing(Throwable th) {
		System.out.println("조퇴를 한다.");
		if(th instanceof OuchException) {
			((OuchException)th).handleException();
		}
	}
	
	public void after() {
		System.out.println("침대와 한몸이 된다.");
	}
	
	///////////////////////////////////
	public void around(ProceedingJoinPoint pjt) {
		int num = 0;
		this.before();
		try {
			// 핵심 관심사항이 동작함
			num = (int)pjt.proceed();
			this.afterReturning(num);
		} catch (Throwable e) {
			this.afterThrowing(e);
		}finally {
			this.after();
		}
		
	}
}
