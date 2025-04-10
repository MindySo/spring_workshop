package com.ssafy.exam.model.service;

import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.ssafy.exam.model.dao.UserDao;
import com.ssafy.exam.model.dto.User;

@Service
public class UserServiceImpl implements UserService{
	private final UserDao userDao;
	
	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void signIn(User user) throws SQLException {
		userDao.insertUser(user);
	}
	
}
