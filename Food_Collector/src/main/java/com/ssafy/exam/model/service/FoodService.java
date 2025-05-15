package com.ssafy.exam.model.service;

import java.io.IOException;
import java.util.List;

import com.ssafy.exam.model.dto.Food;

public interface FoodService {
	void saveFood(Food food);

	Food getFoodByCode(String foodCode);

	List<Food> getAllFoods();;
}
