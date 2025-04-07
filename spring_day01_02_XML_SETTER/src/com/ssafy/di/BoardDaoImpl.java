package com.ssafy.di;

public class BoardDaoImpl implements BoardDao{
	
	public BoardDaoImpl() {
		System.out.println("BoardDaoImpl 생성자 호출");
	}
	
	@Override
	public void insertBoard() {
		System.out.println("BoardDaoImpl -> insertBoard() 호출");
	}
}
