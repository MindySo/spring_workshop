package com.ssafy.exam.model.service;

import java.sql.SQLException;

import com.ssafy.exam.model.dto.User;

public interface UserService {
	void signIn(User user) throws SQLException;
}
