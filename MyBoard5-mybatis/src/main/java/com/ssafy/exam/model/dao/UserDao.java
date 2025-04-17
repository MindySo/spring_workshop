package com.ssafy.exam.model.dao;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.exam.model.dto.User;

//@Mapper
public interface UserDao {
	User selectLogin(User user) throws SQLException;
}







 