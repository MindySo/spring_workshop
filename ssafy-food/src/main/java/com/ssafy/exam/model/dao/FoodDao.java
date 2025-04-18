package com.ssafy.exam.model.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.exam.model.dto.Food;

//@Mapper
public interface FoodDao {
	int insertFood(Food food) throws SQLException;
	List<Food> selectAll() throws SQLException;
	Food selectFood(int no) throws SQLException;
	int deleteFood(int no) throws SQLException;
	void updateFood(Food food) throws SQLException;
	
}
