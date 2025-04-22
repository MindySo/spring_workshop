package com.ssafy.exam.model.service;

import com.ssafy.exam.model.dto.User;

public interface UserService {
	User login(User user) throws Exception;
}
