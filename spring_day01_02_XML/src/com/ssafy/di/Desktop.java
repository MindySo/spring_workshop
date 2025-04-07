package com.ssafy.di;

public class Desktop  implements Computer{
	private String CPU;
	private String RAM;
	private String SSD;
	private String power;
	
	public Desktop() {
		System.out.println("데스크탑 생성");
	}
	
	// 설정자, 접근자, 생성자
	@Override
	public String getInfo() {
		return "데스크톱";
	}
}
