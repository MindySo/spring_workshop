package com.ssafy.exam.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.exam.model.dao.FoodDao;
import com.ssafy.exam.model.dto.Food;

@Service
public class FoodServiceImpl implements FoodService {
	
	
	private final FoodDao foodDao;
	public FoodServiceImpl(FoodDao foodDao) {
		this.foodDao = foodDao;
	}
	
	@Override
	public void write(Food food) throws Exception {
		int no = foodDao.insertFood(food);
	}

	@Override
	public List<Food> list() throws Exception {
		return foodDao.selectAll();
	}

	@Override
	public Food detail(int no) throws SQLException {
		Food food = foodDao.selectFood(no);
		return food;
	}

	@Override
	public void remove(int no) throws SQLException {
		foodDao.deleteFood(no);
		
	}

	@Override
	public void update(Food food) throws Exception {
		foodDao.updateFood(food);
	}

}













