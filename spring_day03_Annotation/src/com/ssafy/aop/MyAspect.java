package com.ssafy.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

// aspect : 공통 관심사항을 저장할 클래스(단위)
@Component
@Aspect
public class MyAspect {
	// 포인트컷 : 여러개의 join point들 중 내가 들어갈 곳을 결정하는 문장
	@Pointcut("execution(* com.ssafy.aop.*.coding(..))")
	public void mypt() {
		// 메서드 내용은 작성하지 않는다.
		// 메서드명 -> id가 된다.
	}

//	@Before("mypt()")
	public void before() {
		System.out.println("컴퓨터를 부팅한다");
	}

//	@AfterReturning(value = "mypt()", returning = "num")
	public void afterReturning(int num) {
		System.out.println(num + "시간을 투자해 git에 commit한다");
	}

//	@AfterThrowing(value = "mypt()", throwing = "th")
	public void afterThrowing(Throwable th) {
		System.out.println("조퇴를 한다.");
		if (th instanceof OuchException) {
			((OuchException) th).handleException();
		}
	}

//	@After("mypt()")
	public void after() {
		System.out.println("침대와 한몸이 된다.");
	}

	///////////////////////////////////
	// Around 사용할 경우 다른 어노테이션은 모두 사용하지 않아야 함
	@Around("mypt()")
	public int around(ProceedingJoinPoint pjt) {
		int num = 0; // 기본값
		this.before();
		try {
			num = (int)pjt.proceed(); // 핵심 메서드 실행
			this.afterReturning(num);
		} catch (Throwable e) {
			this.afterThrowing(e);
		} finally {
			this.after();
		}
		return num;
	}

}
