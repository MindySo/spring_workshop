package com.ssafy.exam.model.dto;

public class User {
	private int userNo;
	private String userId;
	private String name; 
	private String password; 
	private String role;
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	@Override
	public String toString() {
		return "User [userNo=" + userNo + ", userId=" + userId + ", name=" + name + ", password=" + password + ", role="
				+ role + "]";
	}
}
