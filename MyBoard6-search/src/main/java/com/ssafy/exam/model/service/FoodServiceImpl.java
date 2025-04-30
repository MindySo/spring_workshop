package com.ssafy.exam.model.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.exam.external.api.ApiExplorer;
import com.ssafy.exam.model.dto.Food;

@Service
public class FoodServiceImpl implements FoodService{
	
	private final ApiExplorer apiExplorer;
	public FoodServiceImpl(ApiExplorer apiExplorer) {
		this.apiExplorer = apiExplorer;
	}
	
	@Override
	public List<Food> getFoodList(String name) throws IOException {
		List<Food> foodList = apiExplorer.fetchFoodsByName(name);
		for(Food f : foodList) {
			f.setFoodNutri(apiExplorer.fetchNutrientsByFdCode(f.getFdCode()));
		}
	    return foodList;
	}
}
