package com.ssafy.exam.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.ssafy.exam.model.dto.User;
import com.ssafy.exam.util.DBUtil;

@Repository
public class UserDaoImpl implements UserDao {

	private final DBUtil db;
	public UserDaoImpl(DBUtil db) {
		this.db = db;
	}
	
	@Override
	public User selectLogin(User user) throws SQLException {
		try (
			Connection con = db.getConnection();
			PreparedStatement pstmt = con.prepareStatement("""
				select user_id, name, role
				  from users
				 where user_id = ?
				   and password = ?	
			""");	
		) {
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getPassword());
			ResultSet rs = pstmt.executeQuery();
			User loginUser = null;
			if (rs.next()) {
				loginUser = new User();
				loginUser.setUserId(rs.getString("user_id"));
				loginUser.setName(rs.getString("name"));
				loginUser.setRole(rs.getString("role"));
			}
			return loginUser;
		}
	}

}


