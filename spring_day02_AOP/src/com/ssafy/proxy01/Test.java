package com.ssafy.proxy01;

public class Test {
	public static void main(String[] args) {
		Programmer p = new Programmer();
		SSAFY s = new SSAFY();
		
		PersonProxy px = new PersonProxy();
		
//		px.setPerson(p);
		px.setPerson(s);
		px.coding();
	}
}
