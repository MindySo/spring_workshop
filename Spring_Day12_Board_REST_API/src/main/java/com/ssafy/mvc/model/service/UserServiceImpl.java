package com.ssafy.mvc.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.mvc.model.dao.UserDao;
import com.ssafy.mvc.model.dto.User;
@Service
public class UserServiceImpl implements UserService {

	//유저다오 의존성 주입
	@Autowired
	private UserDao userDao;
	
	
	@Override
	public List<User> getUserList() {
		return userDao.selectAll();
	}

	@Override
	public void signup(User user) {
		userDao.insertUser(user);
	}

	@Override
	public User login(String id, String password) {
		Map<String, String> info = new HashMap<>();
		info.put("id", id);
		info.put("password", password);
		User tmp = userDao.selectOne(info);
		
		//비밀번호확인 -> SQL을 이용해서 DB에서 맞는 친구를 들고 올수도 있지만
		//일단 ID로 객체 가지고와서 요기서 비밀번호 일치 판단을 진행할 수 있음
		System.out.println(tmp);
		return tmp;
	}

}
