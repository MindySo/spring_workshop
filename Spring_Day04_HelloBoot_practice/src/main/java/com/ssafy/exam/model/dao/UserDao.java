package com.ssafy.exam.model.dao;

import java.sql.SQLException;

import com.ssafy.exam.model.dto.User;

public interface UserDao {
	int insertUser(User user) throws SQLException ;
}
