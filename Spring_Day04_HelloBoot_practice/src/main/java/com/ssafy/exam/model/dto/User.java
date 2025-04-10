package com.ssafy.exam.model.dto;

import java.io.Serializable;

public class User implements Serializable {
	public int no;
	public String id;
	public String pw;
	
	public User() {
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	@Override
	public String toString() {
		return "User [no=" + no + ", id=" + id + ", pw=" + pw + "]";
	}
	
}
