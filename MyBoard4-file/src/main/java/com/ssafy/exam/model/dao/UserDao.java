package com.ssafy.exam.model.dao;

import java.sql.SQLException;

import com.ssafy.exam.model.dto.User;

public interface UserDao {
	User selectLogin(User user) throws SQLException;
}







