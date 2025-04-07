package com.ssafy.di;

import org.springframework.stereotype.Repository;

//@Component(value = "boardDao")
@Repository(value = "boardDao")
public class BoardDaoImpl implements BoardDao{
	
	public BoardDaoImpl() {
		System.out.println("BoardDaoImpl 생성자 호출");
	}
	
	@Override
	public void insertBoard() {
		System.out.println("BoardDaoImpl -> insertBoard() 호출");
	}
}
