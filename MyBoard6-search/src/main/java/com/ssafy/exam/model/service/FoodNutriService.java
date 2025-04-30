package com.ssafy.exam.model.service;

import java.io.IOException;
import java.util.List;

import com.ssafy.exam.model.dto.Food;
import com.ssafy.exam.model.dto.FoodNutri;

public interface FoodNutriService {
	List<FoodNutri> getFoodNutriList(String fdCode) throws IOException;
}
