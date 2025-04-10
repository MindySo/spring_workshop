package com.ssafy.exam.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.ssafy.exam.model.dto.User;
import com.ssafy.exam.util.DBUtil;

@Repository
public class UserDaoImpl implements UserDao{
	private final DBUtil db;

	public UserDaoImpl(DBUtil db) {
		this.db = db;
	}

	@Override
	public int insertUser(User user) throws SQLException {
		try(
				Connection con = db.getConnection();
				PreparedStatement pstmt = con.prepareStatement("""
						insert into user(id, pw) values (?, ?);
						""");
		){
			pstmt.setString(1, user.id);
			pstmt.setString(2, user.pw);
			pstmt.executeUpdate();
		}
		
		return 0;
	}
}
