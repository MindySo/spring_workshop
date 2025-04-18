package com.ssafy.exam.model.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import com.ssafy.exam.model.dto.Food;

public interface FoodService {
	void write(Food food) throws Exception;
	List<Food> list() throws Exception;
	Food detail(int no) throws SQLException;
	void remove(int no) throws SQLException;
	void update(Food food) throws Exception;
}
